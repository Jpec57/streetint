package com.example.jpec.streetint.activities

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.fragments.main_activity.end_of_workout.EndOfWorkoutContentFragment
import com.example.jpec.streetint.fragments.main_activity.end_of_workout.EndOfWorkoutMuscleFragment
import com.example.jpec.streetint.fragments.main_activity.end_of_workout.EndOfWorkoutResumeFragment
import com.example.jpec.streetint.interfaces.DbWorkerThread
import com.example.jpec.streetint.interfaces.SkillUserInfoDatabase
import com.example.jpec.streetint.interfaces.WorkoutDatabase
import com.example.jpec.streetint.models.ProfileDataModel
import com.example.jpec.streetint.models.Workout

class EndOfWorkoutActivity : FragmentActivity() {
    private lateinit var mPager: ViewPager
    var workouts: List<Workout>? = null
    lateinit var workout: Workout
    var workoutType = 0
    var skillName = ""
    var contextContentFragment : EndOfWorkoutContentFragment? = null

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var mDb: WorkoutDatabase? = null
    private var skillUserInfoDatabase: SkillUserInfoDatabase? = null
    private var mUiHandler = Handler()
    private var skillLevels: ProfileDataModel.SkillLevels? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_of_workout)

        skillName = intent.getStringExtra("skillName")
        workoutType = intent.getIntExtra("type", 0)
        mPager = findViewById(R.id.pager)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mPager.currentItem = 0
        setDatabase()
    }

    override fun onBackPressed() {
        if (mPager.currentItem != 1) {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 3

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

        skillUserInfoDatabase = SkillUserInfoDatabase.getInstance(this)
        mDb = WorkoutDatabase.getInstance(this)
        val intent = intent
        val n = intent.getStringExtra("name")
//        n = "Test"
        getWorkoutsInDb(n)
        getSkillUserInfo()
    }

    private fun getWorkoutsInDb(name: String)
    {
        val task = Runnable {
            workouts = mDb?.workoutDao()?.getWorkoutWithLimit(name, 10)
            workouts?.let {
                workout = it[it.size - 1]
            }
            if (workoutType == 1)
            {
                mUiHandler.post{
                    if (checkIfLvlUp())
                        Toast.makeText(this, "Skill leveled up", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this, "Skill not leveled up", Toast.LENGTH_LONG).show()
                }
            }
            while (contextContentFragment == null) ;
            contextContentFragment!!.onWorkoutRetrieve()
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun checkIfLvlUp() : Boolean
    {
        val goal = intent.getIntExtra("goal", 40)
        val skill = intent.getStringExtra("skillName")
        if (workout.exercises[0].reps >= goal)
        {
            skillLevels!!.skillLevels[skill] = 1 + skillLevels!!.skillLevels[skill]!!
            skillLevels!!.globalSkillLevel = 1 + skillLevels!!.globalSkillLevel
            skillLevels!!.skillPercents[skill] = 0
            setSkillUserInfo()
            return true
        }
        skillLevels!!.skillPercents[skill] = workout.exercises[0].reps * 100 / goal
        setSkillUserInfo()
        return false
    }

    override fun onDestroy() {
        WorkoutDatabase.destroyInstance()
        SkillUserInfoDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

    private fun getSkillUserInfo()
    {
        val task = Runnable {
            skillLevels = skillUserInfoDatabase?.skillUserInfoDao()?.getSkillInfo()
            if (skillLevels == null)
            {
                Log.e("HELIX", "Error with skill levels")
                skillLevels = ProfileDataModel.SkillLevels("Unknown user")
                setSkillUserInfo()
            }
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun setSkillUserInfo()
    {
        val task = Runnable {
            skillUserInfoDatabase?.skillUserInfoDao()?.setSkillInfo(skillLevels!!)
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

}