package com.example.jpec.streetint.Constants

import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout

class HardcodedWorkouts {
    companion object {
        //PREMADE WORKOUTS
        val premadeWorkouts = arrayListOf(
            Workout(name = "First workout", exercises = arrayListOf(
                Exercise(name = "Lower OAP"),
                Exercise(name = "Back Lever", isStatic = true, material = arrayListOf("Pull up bar")),
                Exercise(name = "Dips", material = arrayListOf("Parallel bars", "Pull up bar")))),
            Workout(name="Second Workout", exercises= arrayListOf(
                Exercise(name = "Squat"),
                Exercise(name = "Front Lever", isStatic = true))),
            Workout(name="Test", exercises= arrayListOf(
                Exercise(name = "Squat", series = 1),
                Exercise(name="Hold", series = 2, isStatic = true, reps = 20))),
            Workout(name="Fourth Workout", exercises= arrayListOf(
                Exercise(name = "OAP (Lower)", rest = 90),
                Exercise(name="OAP", rest = 90),
                Exercise(name="Hefesto", rest = 90),
                Exercise(name="Wide Pull ups", series = 6, reps = 7, superset = Exercise("Body rows", series=6, reps=10), rest = 60),
                Exercise(name="Front Lever (Lower)", reps = 10)))
        )

        //SKILLS WORKOUTS
        val allSkills = mutableMapOf(
            "Human Flag" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "V-Sit" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "Handstand" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "Muscle Up" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "Hefesto" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "Pistol Squat" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "Planche" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "Front Lever" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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
            ),
            "Back Lever" to arrayListOf(
                Workout(name = "V-Sit - Level 1", exercises = arrayListOf(
                    Exercise("Compression Hold", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck L-Sit Lift", series = 4, reps = 12, rest = 60),
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

        )

    }
}