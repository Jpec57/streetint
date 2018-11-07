package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChoosePremadeWorkoutAdapter
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.StringList
import com.example.jpec.streetint.models.Workout


class ChoosePremadeWorkoutActivity : Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var allWorkouts: MutableList<Workout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_premade_workout)

        initWorkouts()

        viewManager = LinearLayoutManager(this)
        viewAdapter = ChoosePremadeWorkoutAdapter(this, allWorkouts)

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