package com.example.jpec.streetint.activities

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.RandomWorkoutAdapter
import com.example.jpec.streetint.adapters.ShowWorkoutContentAdapter
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout
import com.example.jpec.streetint.models.computeTimeForWorkout
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_random_workout.*
import kotlinx.android.synthetic.main.dialog_choose_time_duration.*
import org.jetbrains.anko.toast
import kotlin.random.Random

class RandomWorkoutActivity : Activity() {
    private lateinit var adapterMap: MutableMap<String, ArrayList<String>>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var randomAdapter: RandomWorkoutAdapter

    private lateinit var mDbRef : DatabaseReference
    var chosenDifficultyBottom = 0.3
    var chosenDifficultyTop = 0.5
    var difficulty = "Beginner"

    var numberOfExercise = 0
    var maxTime = 120

    var goal = "Strength"
    var serie = 4
    var reps = 10
    var rest = 90

    var targetedMuscles = arrayListOf<String>()
    var availableMaterial = arrayListOf<String>()
    var correspondingExercises = mutableMapOf<String, ArrayList<Exercise>>()
    private lateinit var randomWorkout: Workout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_workout)
        setAdapter()
        setOnClickButtons()
    }

    private fun setOnClickButtons()
    {
        changeTime.text = maxTime.toString()
        start.setOnClickListener {
            setPref()
        }
        changeTime.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_choose_time_duration)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.close.setOnClickListener {
                maxTime = dialog.numberPicker.value
                changeTime.text = maxTime.toString()
                dialog.dismiss()
            }
            dialog.numberPicker.minValue = 5
            dialog.numberPicker.maxValue = 120
            dialog.numberPicker.value = 60
            dialog.show()
        }

    }

    private fun setPref()
    {
        //TODO give a nb of exercises to do
        numberOfExercise = 4
        try {
            for (category in adapterMap.keys)
            {
                    when (category)
                    {
                        "Goal" -> goal = getSelected(category, adapterMap[category]!!, false)[0]
                        "Difficulty"-> difficulty = getSelected(category, adapterMap[category]!!, false)[0]
                        "Targeted muscles" -> targetedMuscles = getSelected(category, adapterMap[category]!!, true)
                        "Available materials"-> availableMaterial = getSelected(category, adapterMap[category]!!, true)
                        else -> Log.e("HELIX", "Not a real category")
                    }
            }
            setGoal()
            setDifficulty()
            fetchExercisesFirebase()

        }catch (e : java.lang.Exception)
        {
            showToast("${e.message}")
        }
    }

    private fun getSelected(category: String, arr: ArrayList<String>, multiple: Boolean) : ArrayList<String>
    {
        val selectedList = randomAdapter.selectedMap
        val selectedListForCategory = ArrayList<String>()
        for (item in arr)
        {
            if (selectedList[item]!!)
            {
                selectedListForCategory.add(item)
                if (!multiple)
                    return selectedListForCategory
            }
        }
        if (selectedListForCategory.size == 0)
            throw Exception("$category is not correctly set")
        return selectedListForCategory
    }

    private fun setDifficulty()
    {
        when(difficulty)
        {
            "Beginner" -> {
                chosenDifficultyBottom = 0.0
                chosenDifficultyTop = 0.2
            }
            "Rookie" -> {
                chosenDifficultyBottom = 0.2
                chosenDifficultyTop = 0.4
            }
            "Intermediate" -> {
                chosenDifficultyBottom = 0.4
                chosenDifficultyTop = 0.6
            }
            "Seasoned" -> {
                chosenDifficultyBottom = 0.6
                chosenDifficultyTop = 0.8
            }
            else -> {
                chosenDifficultyBottom = 0.8
                chosenDifficultyTop = 1.0
            }
        }
    }

    private fun setGoal()
    {
        when(goal){
            "Strength" -> {
                rest = 90
                serie = 5
                reps = 5
            }
            "Cardio" -> {
                rest = 25
                serie = 6
                reps = 15
            }
            else -> {
                rest = 60
                serie = 4
                reps = 10
            }
        }
    }

    private fun setRandomWorkout()
    {
        randomWorkout = Workout(name = "Random Workout", exercises = arrayListOf(
            correspondingExercises[targetedMuscles[0]]!![Random.nextInt(0,correspondingExercises[targetedMuscles[0]]!!.size - 1)]
        ))
        var tmpExercise : Exercise
        randomWorkout.time = computeTimeForWorkout(randomWorkout.exercises)
        while (randomWorkout.time / 60 <= maxTime && randomWorkout.exercises.size < numberOfExercise)
        {
            for (muscle in targetedMuscles)
            {
                val exercisesList = correspondingExercises[muscle]
                tmpExercise = exercisesList!![Random.nextInt(0,exercisesList.size - 1)]
                randomWorkout.exercises.add(tmpExercise)
            }
            randomWorkout.time = computeTimeForWorkout(randomWorkout.exercises)
        }
        randomWorkout.time = computeTimeForWorkout(randomWorkout.exercises)
        Log.e("HELIX", "${randomWorkout.exercises.toString()}")
        randomWorkout.exercises.sortByDescending { it.difficulty }
        Log.e("HELIX", "${randomWorkout.exercises.toString()}")

        val intent = Intent(this, ShowWorkoutContentActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("workout", randomWorkout)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun fetchExercisesFirebase()
    {
        mDbRef = FirebaseDatabase.getInstance().reference.child("exercises")
        val query = mDbRef.orderByChild("difficulty")
            .startAt(chosenDifficultyBottom)
            .endAt(chosenDifficultyTop)

        query.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var nb = 0
                    var exercisesText = ""
                    for (item in dataSnapshot.children)
                    {
                        val musclesList = arrayListOf<String>()
                        val materialList = arrayListOf<String>()
                        var test = false
                        for (material in item.child("material").children)
                        {
                            materialList.add(material.getValue(String::class.java)!!)
                            test = false
                            for (available in availableMaterial)
                            {
                                if (material.getValue(String::class.java) == available)
                                    test = true
                            }
                        }
                        if (!test)
                            continue
                        test = false
                        var it = 0
                        while (!test && it < targetedMuscles.size)
                        {
                            for (muscle in item.child("muscles").children)
                            {
                                musclesList.add(muscle.getValue(String::class.java)!!)
                                if (targetedMuscles[it] == muscle.getValue(String::class.java))
                                {
                                    val diff = item.child("difficulty").getValue(Float::class.java) ?: 0.0f
                                    val isStatic = item.child("hold").getValue(Boolean::class.java) ?: false
                                    insertInCorrespondingExerciseList(targetedMuscles[it],Exercise(name= item.key!!,
                                        muscles = musclesList,
                                        material = materialList,
                                        difficulty = diff,
                                        isStatic = isStatic,
                                        series = serie,
                                        reps = reps,
                                        rest = rest))
                                    exercisesText += item
                                    nb++
                                }
                            }
                            it++
                        }
                    }
                    if (nb == 0)
                    {
                        showToast("There are no exercises matching your criteria")
                    }
                    else
                    {
                        setRandomWorkout()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled ${databaseError.toException()}")
                }
            })
    }

    private fun insertInCorrespondingExerciseList(key: String, exercise: Exercise)
    {
        if (correspondingExercises.containsKey(key))
            correspondingExercises[key]!!.add(exercise)
        else
            correspondingExercises[key] = arrayListOf(exercise)
    }

    private fun getPrefForRandom(): ArrayList<String>
    {
        val muscles = arrayListOf("Back", "Biceps")
        val material = arrayListOf("Pull Up Bar", "Parallel Bars")
        val pref = ArrayList<String>()
        pref.add("Strength")
        pref.add("Seasoned")
        for (m in muscles)
            pref.add(m)
        for (m in material)
            pref.add(m)
        return pref
    }

    private fun setAdapter()
    {
        adapterMap = mutableMapOf()
        adapterMap["Goal"] = arrayListOf("Strength", "Gain mass", "Cardio")
        adapterMap["Difficulty"] = arrayListOf("Beginner", "Rookie", "Intermediate", "Seasoned", "Elite")
        adapterMap["Targeted muscles"] = arrayListOf("Back", "Biceps", "Triceps", "Chest", "Legs", "Abs", "Shoulders")
        adapterMap["Available materials"] = arrayListOf("None", "Parallel Bars", "Pull Up Bar", "Rubber", "Kettlebell", "Weight")

        viewManager = LinearLayoutManager(this)
        randomAdapter = RandomWorkoutAdapter(this, adapterMap, getPrefForRandom())

        viewAdapter = randomAdapter

        recyclerView = findViewById<RecyclerView>(R.id.recycler).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.HORIZONTAL))
    }

    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}