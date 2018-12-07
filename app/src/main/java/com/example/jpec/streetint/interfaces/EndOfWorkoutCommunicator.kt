package com.example.jpec.streetint.interfaces

import com.example.jpec.streetint.models.Workout

interface EndOfWorkoutCommunicator {
    fun getWorkoutFromActivity() : Workout
}