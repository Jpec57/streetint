package com.example.jpec.streetint.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.CreatedWorkoutAdapter
import com.example.jpec.streetint.adapters.ShowWorkoutContentAdapter
import com.example.jpec.streetint.interfaces.CreatedWorkoutFocusListener
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout
import com.example.jpec.streetint.utils.ZoomCenterCardLayoutManager
import kotlinx.android.synthetic.main.activity_created_workout.*
import kotlinx.android.synthetic.main.adapter_created_workout_overview.view.*
import android.view.Display



class CreatedWorkoutActivity : Activity(), CreatedWorkoutFocusListener {
    private lateinit var workouts : ArrayList<Workout>
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private lateinit var recyclerView2: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter2: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager2: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private var width = 0
    private lateinit var currentWorkout :  Workout


    override fun onChangeFocus(boolean: Boolean) {
        if (boolean)
        {
            val view = recyclerView.getChildAt(1)
            if (view != null && view.workoutName != null)
            {
                val pos = (view.number.text.toString()).toInt()
                currentWorkout = workouts[pos]
                setExoAdapter()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        width = displayMetrics.widthPixels
        setContentView(R.layout.activity_created_workout)
        setAdapter()
        setOnClickButtons()
    }

    private fun setAdapter()
    {
        workouts = ArrayList()
        workouts.add(Workout(name = "A", exercises = arrayListOf(Exercise("ExoA"))))
        workouts.add(Workout(name = "B", exercises = arrayListOf(Exercise("Kowjf"), Exercise("Test2"))))
        workouts.add(Workout(name = "C", exercises = arrayListOf(Exercise("Tefqf"), Exercise("wtff2"), Exercise("Test3"))))
        workouts.add(Workout(name = "D", exercises = arrayListOf(Exercise("wfqwst"))))
        workouts.add(Workout(name = "E", exercises = arrayListOf(Exercise("wfnjwjf"))))
        workouts.add(Workout(name = "F", exercises = arrayListOf(Exercise("Twfn"))))

        viewManager = ZoomCenterCardLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false, this)
        viewAdapter = CreatedWorkoutAdapter(this, workouts)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        if (workouts.size > 0)
            recyclerView.smoothScrollBy(width / 4, 0)
    }

    private fun setExoAdapter()
    {
        viewManager2 = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter2 = ShowWorkoutContentAdapter(this, currentWorkout)

        recyclerView2 = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_in).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager2
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter2
        }
    }

    private fun setOnClickButtons()
    {
        start.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("workout", currentWorkout)
            val intent = Intent(this, InWorkoutActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }


}