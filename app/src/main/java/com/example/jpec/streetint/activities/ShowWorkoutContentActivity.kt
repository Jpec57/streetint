package com.example.jpec.streetint.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChoosePremadeWorkoutAdapter
import com.example.jpec.streetint.adapters.ShowWorkoutContentAdapter
import com.example.jpec.streetint.interfaces.DbWorkerThread
import com.example.jpec.streetint.interfaces.WorkoutDatabase

import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.StringList
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.activity_show_workout_content.*

class ShowWorkoutContentActivity : Activity() {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var workout: Workout

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var mDb: WorkoutDatabase? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_workout_content)
        setWorkoutAdapter()
        setDatabase()
        setOnClickButtons()
    }

    private fun setDatabase()
    {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = WorkoutDatabase.getInstance(this)
    }

    private fun bindDataWithUI(workouts: List<Workout>?)
    {
        var allNames = ""
        if (workouts == null || workouts.isEmpty())
        {
            Toast.makeText(this, "The database is empty", Toast.LENGTH_LONG).show()
            return
        }
        for (w in workouts)
        {
            allNames += "${w.name} and ${w.exercises[0].name}\n"
        }
        Toast.makeText(this, allNames, Toast.LENGTH_LONG).show()
    }

    private fun insertWorkoutInDb(workout: Workout)
    {
        val task = Runnable {
            mDb?.workoutDao()?.insertWorkout(workout)
            bindDataWithUI(mDb?.workoutDao()?.getAllWorkouts())
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun deleteWorkoutInDb(workout: Workout)
    {
        val task = Runnable {
            mDb?.workoutDao()?.deleteWorkout(workout.name)
            bindDataWithUI(mDb?.workoutDao()?.getAllWorkouts())
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun testIfWorkoutInDb()
    {
        val task = Runnable {
            val w = mDb?.workoutDao()?.getInitWorkout(workout.name)
            if(w != null)
            {
                save.setImageResource(android.R.drawable.btn_star_big_on)
            }
            else
            {
                save.setImageResource(android.R.drawable.btn_star_big_off)
            }
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun insertOrDeleteWorkoutInDb()
    {
        val task = Runnable {
            val w = mDb?.workoutDao()?.getInitWorkout(workout.name)
            if(w != null)
            {
                save.setImageResource(android.R.drawable.btn_star_big_off)
                deleteWorkoutInDb(workout)
            }
            else
            {
                save.setImageResource(android.R.drawable.btn_star_big_on)
                insertWorkoutInDb(workout)
            }
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    override fun onDestroy() {
        WorkoutDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

    private fun setOnClickButtons()
    {
        testIfWorkoutInDb()
        save.setOnClickListener {
            insertOrDeleteWorkoutInDb()
        }
        edit.setOnClickListener { Toast.makeText(this, "You want to edit this workout", Toast.LENGTH_LONG).show() }
        start.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("workout", workout)
            val intent = Intent(this, InWorkoutActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setWorkoutAdapter()
    {
        val intent = this.intent
        val bundle = intent.extras
        if (bundle != null)
            workout = bundle.getSerializable("workout") as Workout

        time.text = "${workout.time / 60}"
        workout_desc.text = workout.description
        workout_name.text = workout.name
        cycleOrSeries.text = if (workout.cycle) "CYCLES" else "SERIES"
        cycle.setImageResource( if (workout.cycle) R.drawable.cycle else R.drawable.series)


        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter = ShowWorkoutContentAdapter(this, workout)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                recyclerView.context,
                androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
            )
        )
    }

}