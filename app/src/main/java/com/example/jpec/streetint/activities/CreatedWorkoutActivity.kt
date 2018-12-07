package com.example.jpec.streetint.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.CreatedWorkoutAdapter
import com.example.jpec.streetint.adapters.ShowWorkoutContentAdapter
import com.example.jpec.streetint.interfaces.CreatedWorkoutFocusListener
import com.example.jpec.streetint.databases.DbWorkerThread
import com.example.jpec.streetint.databases.WorkoutDatabase
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout
import com.example.jpec.streetint.utils.ZoomCenterCardLayoutManager
import kotlinx.android.synthetic.main.activity_created_workout.*
import kotlinx.android.synthetic.main.adapter_created_workout_overview.view.*
import org.jetbrains.anko.toast


class CreatedWorkoutActivity : Activity(), CreatedWorkoutFocusListener {
    private var workouts : List<Workout>? = null
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private lateinit var recyclerView2: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter2: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager2: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private var width = 0
    private lateinit var currentWorkout :  Workout

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var mDb: WorkoutDatabase? = null
    private var mUiHandler = Handler()


    override fun onChangeFocus(boolean: Boolean) {
        if (boolean)
        {
            val view = recyclerView.getChildAt(1)
            if (view != null && view.workoutName != null)
            {
                val pos = (view.number.text.toString()).toInt()
                currentWorkout = workouts!![pos]
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
        setDatabase()
        setOnClickButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        WorkoutDatabase.destroyInstance()
        mDbWorkerThread.quit()
    }

    private fun setDatabase()
    {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = WorkoutDatabase.getInstance(this)
        loadWorkoutFromDatabase()
    }

    private fun loadWorkoutFromDatabase()
    {
        val task = Runnable {
            workouts = mDb?.workoutDao()?.getSavedWorkouts()
            mUiHandler.post{
                setAdapter()
                if (workouts.isNullOrEmpty())
                {
                    recyclerView.visibility = View.INVISIBLE
                    toast("You have no saved workout yet")
                }
            }
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun setAdapter()
    {
        if (workouts.isNullOrEmpty())
        {
            workouts = listOf(Workout(name = "Example Workout", exercises = arrayListOf(Exercise(name = "None"))))
            start.text = getString(R.string.create_one)
            start.setOnClickListener {
                startActivity(Intent(this, CreateWorkoutActivity::class.java))
            }
        }
        viewManager = ZoomCenterCardLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false, this)
        viewAdapter = CreatedWorkoutAdapter(this, workouts!!)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        if (workouts!!.isNotEmpty())
            recyclerView.smoothScrollBy(width / 4, 0)
    }

    private fun setExoAdapter()
    {
        viewManager2 = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter2 = ShowWorkoutContentAdapter(this, currentWorkout)

        recyclerView2 = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_in).apply {
            setHasFixedSize(true)
            layoutManager = viewManager2
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