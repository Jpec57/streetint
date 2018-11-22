package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChooseSkillAdapter
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout

class ChooseSkillActivity : Activity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_skills)
        setAdapter()
    }

    private fun setAdapter()
    {
        val allSkills = mutableMapOf<String, ArrayList<Workout>>()
        allSkills["Human Flag"] = arrayListOf(
            Workout(name = "Test", exercises = arrayListOf(Exercise("None"))))
        allSkills["V-Sit"] = arrayListOf(
            Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck L-Sit Lift", series = 4, reps = 12, isStatic = true, rest = 60),
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
        allSkills["Handstand"] = arrayListOf(Workout(name = "Test",exercises = arrayListOf(Exercise("None"))))
        allSkills["Muscle Up"] = arrayListOf(Workout(name = "Test",exercises = arrayListOf(Exercise("None"))))
        allSkills["Hefesto"] = arrayListOf(Workout(name = "Test",exercises = arrayListOf(Exercise("None"))))
        allSkills["Pistol Squat"] = arrayListOf(Workout(name = "Test",exercises = arrayListOf(Exercise("None"))))
        allSkills["Planche"] = arrayListOf(Workout(name = "Test",exercises = arrayListOf(Exercise("None"))))
        allSkills["Front Lever"] = arrayListOf(Workout(name = "Test",exercises = arrayListOf(Exercise("None"))))
        allSkills["Back Lever"] = arrayListOf(Workout(name = "Test",exercises = arrayListOf(Exercise("None"))))

        viewManager = LinearLayoutManager(this)
        viewAdapter = ChooseSkillAdapter(this, allSkills)

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