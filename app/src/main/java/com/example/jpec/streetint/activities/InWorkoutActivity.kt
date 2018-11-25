package com.example.jpec.streetint.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.example.jpec.streetint.R
import com.example.jpec.streetint.fragments.main_activity.in_workout.WorkoutCountdownViewFragment
import com.example.jpec.streetint.fragments.main_activity.in_workout.WorkoutExerciseViewFragment
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout
import com.example.jpec.streetint.utils.LockableViewPager


class InWorkoutActivity : FragmentActivity() {
    val NUM_PAGES = 2
    lateinit var mPager: LockableViewPager
    var workout: Workout? = null
    var time: Int = 0
    var currentSerie = 1
    var currentExo = 0
    var doneWorkout: Workout? = null
    var workoutType = 0
    var goal = 30
    var skillName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_workout)

        val bundle = intent.extras
        if (bundle != null)
        {
            workout = bundle.getSerializable("workout") as Workout
            workoutType = intent.getIntExtra("type", 0)
            skillName = intent.getStringExtra("skillName")
            goal = intent.getIntExtra("goal", 30)
            doneWorkout = workout!!.copy()
            doneWorkout!!.exercises = ArrayList()
        }
        launchTime()


        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager)
        mPager.setSwipePagingEnabled(false)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment =
            when (position)
            {
                0 -> WorkoutExerciseViewFragment()
                else -> WorkoutCountdownViewFragment()

            }
    }

    private fun launchTime()
    {
        val handler = Handler()
        val delay: Long = 1000

        handler.postDelayed(object : Runnable {
            override fun run() {
                time += 1
                handler.postDelayed(this, delay)
            }
        }, delay)
    }
}