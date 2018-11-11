package com.example.jpec.streetint.activities

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.example.jpec.streetint.R
import com.example.jpec.streetint.fragments.main_activity.end_of_workout.EndOfWorkoutContentFragment
import com.example.jpec.streetint.fragments.main_activity.end_of_workout.EndOfWorkoutMuscleFragment
import com.example.jpec.streetint.fragments.main_activity.end_of_workout.EndOfWorkoutResumeFragment
import com.example.jpec.streetint.interfaces.DbWorkerThread
import com.example.jpec.streetint.interfaces.WorkoutDatabase
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.activity_end_of_workout.*
import kotlinx.android.synthetic.main.fragment_end_of_workout_content.*

class EndOfWorkoutActivity : FragmentActivity() {
    val NUM_PAGES = 3
    private lateinit var mPager: ViewPager
    var workouts: List<Workout>? = null
    lateinit var workout: Workout

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var mDb: WorkoutDatabase? = null
    private val mUiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_of_workout)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mPager.currentItem = 1
        setDatabase()
    }

    override fun onBackPressed() {
        if (mPager.currentItem != 1) {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment =
            when (position)
            {
                0 ->EndOfWorkoutContentFragment()
                1 -> EndOfWorkoutResumeFragment()
                else -> EndOfWorkoutMuscleFragment()
            }
    }

    private fun setDatabase()
    {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = WorkoutDatabase.getInstance(this)
        val intent = intent
        getWorkoutsInDb(intent.getStringExtra("name"))
    }

    private fun getWorkoutsInDb(name: String)
    {
        val task = Runnable {
            //TODO change name with what is received
            workouts = mDb?.workoutDao()?.getWorkout(name)
            workouts?.let {
                workout = it[it.size - 1]
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

}