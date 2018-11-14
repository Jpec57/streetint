package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.RandomWorkoutAdapter
import com.example.jpec.streetint.adapters.ShowWorkoutContentAdapter
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_random_workout.*
import kotlin.random.Random

class RandomWorkoutActivity : Activity() {
    val TAG = "JPECJPEC"
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var mDbRef : DatabaseReference
    var chosenDifficultyBottom = 0.3
    var chosenDifficultyTop = 0.5

    var numberOfExercise = 0
    var maxTime = 120

    var goal = "Strength"
    var serie = 4
    var reps = 10
    var rest = 90

    var targetedMuscles = arrayListOf<String>()
    var availableMaterial = arrayListOf("None")
    var correspondingExercises = mutableMapOf<String, ArrayList<Exercise>>()
    var exercisesMuscle = arrayListOf<Exercise>()
    private lateinit var randomWorkout: Workout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_workout)
        setAdapter()
        setPref()
        setGoal()
        fetchExercisesFirebase()
    }

    private fun setPref()
    {
        targetedMuscles.add("Back")
        availableMaterial.add("Pull Up Bar")

//        targetedMuscles.add("Chest")
//        availableMaterial.add("Parallel Bar")
        goal = "strength"
        numberOfExercise = 4
    }

    private fun setGoal()
    {
        when(goal){
            "strength" -> {
                rest = 90
                serie = 5
                reps = 5
            }
            "cardio" -> {
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
        while (randomWorkout.time <= maxTime && randomWorkout.exercises.size < numberOfExercise)
        {
            for (muscle in targetedMuscles)
            {
                val exercisesList = correspondingExercises[muscle]
                tmpExercise = exercisesList!![Random.nextInt(0,exercisesList.size - 1)]
                randomWorkout.exercises.add(tmpExercise)
            }
        }
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
                                    insertInCorrespondingExerciseList(targetedMuscles[it],Exercise(name= item.key!!,
                                        muscles = musclesList,
                                        material = materialList,
                                        series = serie,
                                        reps = reps,
                                        rest = rest))
                                    exercisesText += item
                                }
                            }
                            it++
                        }
                    }
                    setRandomWorkout()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled ${databaseError.toException()}")
                }
            })
    }

    private fun insertInCorrespondingExerciseList(key: String, exercise: Exercise)
    {
        if (correspondingExercises.containsKey(key))
        {
            correspondingExercises[key]!!.add(exercise)
        }
        else
        {
            correspondingExercises[key] = arrayListOf(exercise)
        }
    }

    private fun setAdapter()
    {
        val adapterMap = mutableMapOf<String, ArrayList<String>>()
        adapterMap["Goal"] = arrayListOf("Strength", "Gain mass", "Cardio")
        adapterMap["Difficulty"] = arrayListOf("Beginner", "Rookie", "Intermediate", "Seasoned", "Elite")
        adapterMap["Targeted muscles"] = arrayListOf("Back", "Biceps", "Triceps", "Chest", "Legs", "Abs", "Shoulders")
        adapterMap["Available materials"] = arrayListOf("Parallel Bars", "Pull Up Bar", "Rubber", "Kettlebell", "Weight")
        viewManager = LinearLayoutManager(this)
        viewAdapter = RandomWorkoutAdapter(this, adapterMap)

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