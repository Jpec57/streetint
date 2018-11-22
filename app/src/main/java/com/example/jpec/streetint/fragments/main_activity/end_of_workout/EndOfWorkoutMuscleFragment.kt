package com.example.jpec.streetint.fragments.main_activity.end_of_workout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.activity_create_workout.*

class EndOfWorkoutMuscleFragment : Fragment() {
    private val muscleColorMap = mapOf("chest" to R.style.chest,
        "biceps" to R.style.biceps, "triceps" to R.style.triceps,
        "leg" to R.style.leg, "shoulder" to R.style.shoulders,
        "trapezius" to R.style.trapezius, "back" to R.style.back,
        "abs" to R.style.abs)
    private lateinit var selectedMuscles : ArrayList<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_end_of_workout_muscles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedMuscles = arrayListOf("back", "abs", "leg")
        setMuscleFilters()
    }

    private fun setMuscleFilters()
    {
        val theme = resources.newTheme()
        for (m in selectedMuscles)
            theme.applyStyle(muscleColorMap[m]!!, false)

        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_paths, theme)
        svg.setImageDrawable(drawable)
    }
}