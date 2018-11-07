package com.example.jpec.streetint.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var workout: Workout

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var mDb: WorkoutDatabase? = null
    private val mUiHandler = Handler()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_workout_content)
        setWorkoutAdapter()
        setOnClickButtons()
        setDatabase()

    }

    private fun setDatabase()
    {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = WorkoutDatabase.getInstance(this)
    }


    private fun fetchWorkoutsFromDb()
    {
        val task = Runnable {
            val workouts = mDb?.workoutDao()?.getAll()
            bindDataWithUI(workouts)
        }

        mDbWorkerThread.postTask(task)
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
            mDb?.workoutDao()?.insert(workout)
            bindDataWithUI(mDb?.workoutDao()?.getAll())
        }
        mDbWorkerThread.postTask(task)
    }

    private fun deleteWorkoutInDb(workout: Workout)
    {
        val task = Runnable {
            mDb?.workoutDao()?.delete(workout.name)
            bindDataWithUI(mDb?.workoutDao()?.getAll())
        }
        mDbWorkerThread.postTask(task)
    }

    override fun onDestroy() {
        WorkoutDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

    private fun setOnClickButtons()
    {
        save.setOnClickListener {
            if(workout.saved)
            {
                save.setImageResource(android.R.drawable.btn_star_big_off)
                deleteWorkoutInDb(workout)
            }
            else
            {
                save.setImageResource(android.R.drawable.btn_star_big_on)
                insertWorkoutInDb(workout)
            }
            workout.saved = !workout.saved
        }
        edit.setOnClickListener { Toast.makeText(this, "You want to edit this workout", Toast.LENGTH_LONG).show() }
        start.setOnClickListener {
            val intent = Intent(this, InWorkoutActivity::class.java)
            intent.putExtra("workout", workout)
            startActivity(intent)
        }
    }

    private fun setWorkoutAdapter()
    {
        val exercises : ArrayList<Exercise> = arrayListOf(
            Exercise(name = "Lower OAP"),
            Exercise(name = "Back Lever", isStatic = true, material = arrayListOf("Pull up bar")),
            Exercise(name = "Dips", material = arrayListOf("Parallel bars", "Pull up bar")))
        workout = Workout(name = "First workout", exercises = exercises)


//        val bundle = intent.extras
//        var nullableWorkout : Workout?
//        if (bundle != null)
//        {
//            nullableWorkout = bundle.getParcelable("workout")
//            if (nullableWorkout != null)
//            {
//                workout = nullableWorkout
//            }
//        }
        val intent = this.intent
        val bundle = intent.extras
        var nullableWorkout : Workout?
        if (bundle != null)
        {
            nullableWorkout = bundle.getSerializable("workout") as Workout
            if (nullableWorkout != null)
            {
                workout = nullableWorkout
            }
        }
        Log.e("Jpec", workout.name)
        Toast.makeText(this, "Test ${workout.name}", Toast.LENGTH_LONG).show()

        time.text = "${workout.time / 60}"
        workout_desc.text = workout.description
        workout_name.text = workout.name
        cycleOrSeries.text = if (workout.cycle) "CYCLES" else "SERIES"
        cycle.setImageResource( if (workout.cycle) R.drawable.cycle else R.drawable.series)


        viewManager = LinearLayoutManager(this)
        viewAdapter = ShowWorkoutContentAdapter(this, workout)

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

}