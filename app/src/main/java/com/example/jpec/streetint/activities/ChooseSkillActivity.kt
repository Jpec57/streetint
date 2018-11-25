package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChooseSkillAdapter
import com.example.jpec.streetint.interfaces.DbWorkerThread
import com.example.jpec.streetint.interfaces.SkillUserInfoDatabase
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.ProfileDataModel
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.activity_choose_skills.*

class ChooseSkillActivity : Activity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var skillLevels: ProfileDataModel.SkillLevels? = null
    private var skillUserInfoDatabase: SkillUserInfoDatabase? = null
    private var mUiThread = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_skills)
        setDatabase()
    }

    override fun onDestroy() {
        SkillUserInfoDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

    private fun setDatabase()
    {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        skillUserInfoDatabase = SkillUserInfoDatabase.getInstance(this)
        getSkillUserInfo()
    }

    private fun getSkillUserInfo()
    {
        val task = Runnable {
            skillLevels = skillUserInfoDatabase?.skillUserInfoDao()?.getSkillInfo()
            if (skillLevels == null)
            {
                skillLevels = ProfileDataModel.SkillLevels("Unknown user")
                setSkillUserInfo()
            }
            mUiThread.post {
                val lvl = "lvl ${skillLevels!!.globalSkillLevel}"
                lvlUser.text = lvl
                setAdapter()
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

    private fun setAdapter()
    {

        val allSkills = mutableMapOf<String, ArrayList<Workout>>()
        allSkills["Human Flag"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["V-Sit"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["Handstand"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["Muscle Up"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["Hefesto"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["Pistol Squat"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["Planche"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["Front Lever"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )
        allSkills["Back Lever"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Crunch", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 2", exercises = arrayListOf(
                Exercise("Tuck L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression Hold", series = 4, reps = 25, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 3", exercises = arrayListOf(
                Exercise("L-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 4", exercises = arrayListOf(
                Exercise("Tuck V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Tuck L-Sit", series = 4, reps = 10, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25))),
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)))
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = ChooseSkillAdapter(this, allSkills, skillLevels!!)

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