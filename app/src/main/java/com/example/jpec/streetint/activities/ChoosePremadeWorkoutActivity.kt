package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChoosePremadeWorkoutAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import android.widget.Toast
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.StringList
import com.example.jpec.streetint.models.Workout


class ChoosePremadeWorkoutActivity : Activity() {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var allWorkouts: MutableList<Workout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_premade_workout)

        initWorkouts()

        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter = ChoosePremadeWorkoutAdapter(this, allWorkouts)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                recyclerView.context,
                androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
            )
        )


    }

    private fun initWorkouts()
    {
        val exercises : ArrayList<Exercise> = arrayListOf(Exercise(name = "Lower OAP"),
            Exercise(name = "Back Lever", isStatic = true, material = arrayListOf("Pull up bar")),
            Exercise(name = "Dips", material = arrayListOf("Parallel bars", "Pull up bar")))
        val exercises2 : ArrayList<Exercise> = arrayListOf(Exercise(name = "Squat"),
            Exercise(name = "Front Lever", isStatic = true))
        val exercises3 : ArrayList<Exercise> = arrayListOf(Exercise(name = "Squat", series = 1), Exercise(name="Hold", series = 2, isStatic = true, reps = 20))
        val exercises4 : ArrayList<Exercise> = arrayListOf(
            Exercise(name = "OAP (Lower)", rest = 90),
            Exercise(name="OAP", rest = 90),
            Exercise(name="Hefesto", rest = 90),
            Exercise(name="Wide Pull ups", series = 6, reps = 7, superset = Exercise("Body rows", series=6, reps=10), rest = 60),
            Exercise(name="Front Lever (Lower)", reps = 10))

        allWorkouts = mutableListOf<Workout>()
        allWorkouts.add(Workout(name = "First workout", exercises = exercises))
        allWorkouts.add(Workout(name="Second Workout", exercises= exercises2))
        allWorkouts.add(Workout(name="Test", exercises= exercises3))
        allWorkouts.add(Workout(name="Fourth Workout", exercises= exercises4))

    }
}