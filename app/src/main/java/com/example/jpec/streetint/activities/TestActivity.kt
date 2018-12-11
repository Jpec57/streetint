package com.example.jpec.streetint.activities


import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintSet
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChooseSkillAdapter
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.ProfileDataModel
import com.example.jpec.streetint.models.Workout
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : Activity() {
    private val TAG = "TestActivity"
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private var skillLevels: ProfileDataModel.SkillLevels = ProfileDataModel.SkillLevels("Unknown User")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        setAdapter()
//        val constraintSet = ConstraintSet()
//        constraintSet.clone()
    }

    private fun setAdapter()
    {

        val allSkills = mutableMapOf<String, ArrayList<Workout>>()
        allSkills["Human Flag"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["V-Sit"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["Handstand"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["Muscle Up"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["Hefesto"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["Pistol Squat"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["Planche"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["Front Lever"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )
        allSkills["Back Lever"] = arrayListOf(
            Workout(name = "V-Sit - Level 5", exercises = arrayListOf(
                Exercise("V-Sit", series = 4, reps = 30, isStatic = true, rest= 60),
                Exercise("Tuck V-Sit", series = 4, reps = 15, isStatic = true, rest = 60),
                Exercise("L-Sit", series = 4, reps = 20, isStatic = true, rest = 60),
                Exercise("Compression", series = 6, reps = 12, isStatic = false, rest = 25)
            ))
        )

        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter = ChooseSkillAdapter(this, allSkills, skillLevels)

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