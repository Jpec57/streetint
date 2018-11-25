package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChooseSkillProgressionAdapter
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.activity_choose_skills.*

class ChooseSkillProgressionLvlActivity : Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var workouts: ArrayList<Workout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_skill_progression_lvl)
        setAdapter()
    }

    private fun setAdapter()
    {
        val bundle = intent.extras
        if (bundle != null)
            workouts = bundle.getSerializable("workouts") as ArrayList<Workout>
        val lvl = intent.getIntExtra("lvl", 1)
        lvlUser.text = "Skill lvl $lvl"
        viewManager = LinearLayoutManager(this)
        viewAdapter = ChooseSkillProgressionAdapter(this, workouts, lvl)
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