package com.example.jpec.streetint.fragments.create_workout

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.CreateWorkoutActivity
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import kotlinx.android.synthetic.main.fragment_choose_muscle.*

class MuscleFragment : Fragment() {
    private lateinit var communicator: CreateWorkoutCommunicator
    private val muscleColorMap = mapOf("chest" to R.style.chest,
        "biceps" to R.style.biceps, "triceps" to R.style.triceps,
        "leg" to R.style.leg, "shoulder" to R.style.shoulders,
        "trapezius" to R.style.trapezius, "back" to R.style.back,
        "abs" to R.style.abs)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_muscle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickButtons()
        setMuscleFilters()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        communicator = activity as CreateWorkoutCommunicator
    }

    private fun setMuscleFilters()
    {
        val theme = resources.newTheme()
        val muscles = communicator.getSelectedMusclesArray()
        for (m in muscles)
        {
            theme.applyStyle(muscleColorMap[m.toLowerCase()]!!, false)
        }
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_paths, theme)
        svg.setImageDrawable(drawable)
    }

    private fun selectMuscle(muscle : String)
    {
        showToast(muscle)
        communicator.setMuscle(muscle)
    }

    private fun setOnClickButtons()
    {
        val onClick = View.OnClickListener {
            it as Button
            selectMuscle(it.text.toString().capitalize())
        }
        val views = arrayListOf<Button>(penis, back, quadri_left, quadri_right,
            shoulder_back_left, shoulder_back_right, shoulder_front_left, shoulder_front_right,
            triceps_left, triceps_right, trapezius, butt, hamstring_left, hamstring_right,
            abs, chest, biceps_left, biceps_right)
        for (v in views)
        {
            v.setOnClickListener(onClick)
        }
    }

    fun showToast(message: String) = Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

}