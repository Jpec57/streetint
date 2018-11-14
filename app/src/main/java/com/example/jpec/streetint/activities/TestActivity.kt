package com.example.jpec.streetint.activities


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout
import com.example.jpec.streetint.utils.saveTo
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_test.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File


class TestActivity : Activity() {
    private lateinit var mDbRef : DatabaseReference
    var chosenDifficultyBottom = 0.3
    var chosenDifficultyTop = 0.5

    var numberOfExercise = 1
    var maxTime = 60

    var goal = "Strength"
    var serie = 4
    var reps = 10
    var rest = 90

    var targetedMuscles = arrayListOf<String>()
    var availableMaterial = arrayListOf("None")
    var correspondingExercises = arrayListOf<Exercise>()
    private var randomWorkout: Workout = Workout(name = "Random Workout", exercises = arrayListOf(Exercise("None")))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setPref()
        setGoal()

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
                                    test = true
                            }
                            it++
                        }
                        if (!test)
                            continue
                        correspondingExercises.add(
                            Exercise(name= item.key!!,
                                muscles = musclesList,
                                material = materialList,
                                series = serie,
                                reps = reps,
                                rest = rest))
                        exercisesText += item

                    }
                    textView.text = exercisesText
                    getExercises()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled ${databaseError.toException()}")
                }
            })
/*
        doAsync{
            ("https://drive.google.com/uc?export=download&" +
                    "id=12y2cfzhdW5ExSrU0d_G0jvejPLZDp7fA")
                .saveTo(filesDir.path + "/Exercises")
//https://streetint.firebaseio.com/.json?print=pretty&format=export&download=streetint-export.json&auth=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZG1pbiI6dHJ1ZSwiZXhwIjoxNTQyMDQxMDE4LCJpYXQiOjE1NDIwMzc0MTgsInYiOjB9._JEhhNO6TpJZ8tw9wBeihFu0onOcbuPS580Uqy4qvDE
            var result = ""
            val file = File(filesDir, "Exercises")
            file.forEachLine {
                result += it
            }
            uiThread {
                textView.text = result
            }
        }*/
    }

    private fun setPref()
    {
        targetedMuscles.add("Back")
        availableMaterial.add("Pull Up Bar")

//        targetedMuscles.add("Chest")
//        availableMaterial.add("Parallel Bar")
        goal = "Strength"
        numberOfExercise = 3
    }

    private fun setGoal()
    {
        when(goal){
            "strength" -> {
                rest = 90
                serie = 5
                reps = 5
            }
            else -> {
                rest = 60
                serie = 4
                reps = 10
            }
        }
    }

    private fun getExercises()
    {
        showToast("Number of exercises found ${correspondingExercises.size}")
    }

    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}