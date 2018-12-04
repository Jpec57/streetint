package com.example.jpec.streetint.interfaces

import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout

interface CreateWorkoutCommunicator {
    //MuscleFragment
    fun setMuscle(m : String)

    //ChooseExoFragment
    fun getMuscle() : String
    fun getSelectedMusclesArray() : ArrayList<String>

    //ResumeWorkoutFragment
    fun addExercise(exo : Exercise)

    //ResumeWorkoutFragment
    fun getWorkout() : Workout
    fun setWorkout(workout: Workout)
    fun goBackToMuscle()
}