package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.example.jpec.streetint.constants.HardcodedWorkouts
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChooseSkillAdapter
import com.example.jpec.streetint.databases.DbWorkerThread
import com.example.jpec.streetint.databases.SkillUserInfoDatabase
import com.example.jpec.streetint.models.ProfileDataModel
import kotlinx.android.synthetic.main.activity_choose_skills.*

class ChooseSkillActivity : Activity()
{
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

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

        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter = ChooseSkillAdapter(this, HardcodedWorkouts.allSkills, skillLevels!!)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
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