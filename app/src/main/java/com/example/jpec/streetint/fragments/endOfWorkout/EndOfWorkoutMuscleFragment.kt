package com.example.jpec.streetint.fragments.endOfWorkout

import android.content.Context
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.interfaces.EndOfWorkoutCommunicator
import com.example.jpec.streetint.constants.Constants
import kotlinx.android.synthetic.main.fragment_choose_muscle.*

class EndOfWorkoutMuscleFragment : androidx.fragment.app.Fragment() {
    private lateinit var communicator: EndOfWorkoutCommunicator
    private val selectedMuscles = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_end_of_workout_muscles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMuscleFilters()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        communicator = activity as EndOfWorkoutCommunicator
        val workout = communicator.getWorkoutFromActivity()
        for (e in workout.exercises)
            for (m in e.muscles)
                if (!selectedMuscles.contains(m))
                    selectedMuscles.add(m.toLowerCase())
    }

    private fun setMuscleFilters()
    {
        val theme = resources.newTheme()
        for (m in selectedMuscles)
            theme.applyStyle(Constants.muscleColorMap[m]!!, false)

        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_paths, theme)
        svg.setImageDrawable(drawable)
    }
}