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
import com.example.jpec.streetint.R
import com.example.jpec.streetint.fragments.main_activity.in_workout.WorkoutCountdownViewFragment
import com.example.jpec.streetint.fragments.main_activity.in_workout.WorkoutExerciseViewFragment
import com.example.jpec.streetint.models.Workout
import com.example.jpec.streetint.utils.LockableViewPager


class InWorkoutActivity : FragmentActivity() {
    val NUM_PAGES = 2
    lateinit var mPager: LockableViewPager
    var workout: Workout? = null
    var time: Int = 0
    var currentSerie = 1
    var currentExo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_workout)

        //get data
        val bundle = intent.extras
        if (bundle != null)
        {
//            workout = bundle.getParcelable("workout")
            workout = bundle.getSerializable("workout") as Workout

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
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
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