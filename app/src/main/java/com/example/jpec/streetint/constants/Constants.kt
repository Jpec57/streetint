package com.example.jpec.streetint.constants

import com.example.jpec.streetint.R

class Constants {
    companion object {
        val muscleColorMap = mapOf("chest" to R.style.chest,
            "biceps" to R.style.biceps, "triceps" to R.style.triceps,
            "leg" to R.style.leg, "shoulder" to R.style.shoulders,
            "trapezius" to R.style.trapezius, "back" to R.style.back,
            "abs" to R.style.abs)
        val goal = arrayListOf("Strength", "Gain mass", "Cardio")
        val difficultyLevels = arrayListOf("Beginner", "Rookie", "Intermediate", "Seasoned", "Elite")
        val muscleList = arrayListOf("Back", "Biceps", "Triceps", "Chest", "Legs", "Abs", "Shoulders")
        val extendedMuscleList = arrayListOf("All").also { it.addAll(muscleList) }
        val materialList = arrayListOf("None", "Parallel Bars", "Pull Up Bar", "Rubber", "Kettlebell", "Weight")
        val skills = arrayListOf("Human Flag", "Hefesto", "Front Lever", "Back Lever", "V-Sit", "Handstand Push Up",
            "Muscle Up", "Pistol Squat", "Planche")
    }
}