package com.example.jpec.streetint.activities

import android.os.Bundle
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.fragments.create_workout.ChooseExoFragment
import com.example.jpec.streetint.fragments.create_workout.MuscleFragment
import com.example.jpec.streetint.fragments.create_workout.ResumeWorkoutFragment
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.utils.LockableViewPager

class CreateWorkoutActivity : androidx.fragment.app.FragmentActivity(), CreateWorkoutCommunicator {
    val NUM_PAGES = 3
    lateinit var mPager: LockableViewPager

    var selectedMuscle = ""
    var selectedExo : Exercise? = null
    var exercises = ArrayList<Exercise>()
    lateinit var selectedMuscles : ArrayList<String>

    override fun addExercise(exo: Exercise) {
        exercises.add(exo)
        mPager.currentItem = mPager.currentItem + 1
    }
    override fun setMuscle(m: String) {
        selectedMuscle = m
        if (selectedMuscle !in selectedMuscles)
            selectedMuscles.add(m)
        mPager.currentItem = mPager.currentItem + 1
    }

    override fun getMuscle() = selectedMuscle

    override fun getSelectedMusclesArray() = selectedMuscles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)

        mPager = findViewById(R.id.pager)
        mPager.setSwipePagingEnabled(false)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter

        selectedMuscles = ArrayList()
        mPager.currentItem = 3

    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): androidx.fragment.app.Fragment =
            when (position)
            {
                0 -> MuscleFragment()
                1 -> ChooseExoFragment()
                else -> ResumeWorkoutFragment()
            }
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}