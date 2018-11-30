package com.example.jpec.streetint.interfaces

import com.example.jpec.streetint.models.Exercise

interface CreateWorkoutCommunicator {
    //MuscleFragment
    fun setMuscle(m : String)

    //ChooseExoFragment
    fun getMuscle() : String
    fun getSelectedMusclesArray() : ArrayList<String>

    //ResumeWorkoutFragment

    fun addExercise(exo : Exercise)
}