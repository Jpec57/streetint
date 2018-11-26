package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChooseSkillProgressionAdapter
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.activity_choose_skills.*

class ChooseSkillProgressionLvlActivity : Activity() {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
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
        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter = ChooseSkillProgressionAdapter(this, workouts, lvl)
        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
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