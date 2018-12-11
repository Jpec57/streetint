package com.example.jpec.streetint.constants

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
                Workout(name = "Human Flag - Level 1", exercises = arrayListOf(
                    Exercise("Hanging Side Raise", series = 4, reps = 10, isStatic = false, rest= 60),
                    Exercise("Plank", series = 3, reps = 60, isStatic = true, rest = 60),
                    Exercise("Side Plank", series = 4, reps = 30, isStatic = true, rest = 5),
                    Exercise("Side Plank Raise", series = 4, reps = 15, isStatic = false, rest = 5))),
                Workout(name = "Human Flag - Level 2", exercises = arrayListOf(
                    Exercise("Windshield Wiper", series = 4, reps = 10, isStatic = false, rest= 60),
                    Exercise("Hanging Side Raise", series = 4, reps = 10, isStatic = false, rest= 60),
                    Exercise("Dragon Flag", series = 4, reps = 8, isStatic = false, rest= 60),
                    Exercise("Side Plank Raise", series = 4, reps = 15, isStatic = false, rest = 5))),
                Workout(name = "Human Flag - Level 3", exercises = arrayListOf(
                    Exercise("Incline Human Flag", series = 4, reps = 15, isStatic = true, rest= 60),
                    Exercise("Windshield Wiper", series = 4, reps = 8, isStatic = false, rest= 60),
                    Exercise("Dragon Flag", series = 4, reps = 8, isStatic = false, rest= 60),
                    Exercise("Side Plank Raise", series = 4, reps = 15, isStatic = false, rest = 5))),
                Workout(name = "Human Flag - Level 4", exercises = arrayListOf(
                    Exercise("Human Flag Lower", series = 6, reps = 5, isStatic = false, rest= 60),
                    Exercise("Incline Human Flag", series = 3, reps = 10, isStatic = true, rest= 60),
                    Exercise("Windshield Wiper", series = 4, reps = 8, isStatic = false, rest= 60),
                    Exercise("Dragon Flag", series = 4, reps = 8, isStatic = false, rest= 60))),
                Workout(name = "Human Flag - Level 5", exercises = arrayListOf(
                    Exercise("Human Flag", series = 3, reps = 10, isStatic = true, rest= 60),
                    Exercise("Human Flag Lower", series = 4, reps = 5, isStatic = false, rest= 60),
                    Exercise("Incline Human Flag", series = 3, reps = 15, isStatic = true, rest= 60),
                    Exercise("Windshield Wiper", series = 4, reps = 8, isStatic = false, rest= 60)))
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
            "Handstand Push Up" to arrayListOf(
                Workout(name = "Handstand Push Up - Level 1", exercises = arrayListOf(
                    Exercise("Pike Push Up", series = 6, reps = 10, isStatic = false, rest= 60),
                    Exercise("Hindu Push Up", series = 3, reps = 12, rest = 60),
                    Exercise("Handstand", series = 6, reps = 10, isStatic = true, rest = 60),
                    Exercise("Wall Handstand", series = 2, reps = 20, isStatic = true, rest = 25))),
                Workout(name = "Handstand Push Up - Level 2", exercises = arrayListOf(
                    Exercise("Elevated Pike Push Up", series = 4, reps = 10, isStatic = false, rest= 60),
                    Exercise("Wall Handstand", series = 3, reps = 20, rest = 60),
                    Exercise("Pike Push Up", series = 3, reps = 12, rest = 60),
                    Exercise("Handstand", series = 6, reps = 15, isStatic = true, rest = 25))),
                Workout(name = "Handstand Push Up - Level 3", exercises = arrayListOf(
                    Exercise("Wall Handstand Push Up", series = 4, reps = 8, rest= 60),
                    Exercise("Elevated Pike Push Up", series = 4, reps = 12, rest = 60),
                    Exercise("Handstand", series = 3, reps = 20, isStatic = true, rest = 60),
                    Exercise("Frog Stand", series = 3, reps = 12, isStatic = false, rest = 25))),
                Workout(name = "Handstand Push Up - Level 4", exercises = arrayListOf(
                    Exercise("Handstand Push Up Lower", series = 4, reps = 8, rest= 60),
                    Exercise("Wall Handstand Push Up", series = 4, reps = 5, rest = 60),
                    Exercise("Handstand", series = 3, reps = 25, isStatic = true, rest = 60),
                    Exercise("Elevated Pike Push Up", series = 3, reps = 15, isStatic = false, rest = 25))),
                Workout(name = "Handstand Push Up - Level 5", exercises = arrayListOf(
                    Exercise("Handstand Push Up", series = 4, reps = 5, rest= 60),
                    Exercise("Wall Handstand Push Up", series = 4, reps = 8, isStatic = false, rest = 60),
                    Exercise("Handstand", series = 3, reps = 20, isStatic = true, rest = 60),
                    Exercise("Elevated Pike Push Up", series = 3, reps = 12, isStatic = false, rest = 25)))
            ),
            "Muscle Up" to arrayListOf(
                Workout(name = "Muscle Up - Level 1", exercises = arrayListOf(
                    Exercise("Pull Up", series = 4, reps = 10, isStatic = false, rest= 60),
                    Exercise("Body Row", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Dip", series = 4, reps = 8, isStatic = false, rest = 60),
                    Exercise("Bar Dip", series = 4, reps = 10, isStatic = false, rest = 60))),
                Workout(name = "Muscle Up - Level 2", exercises = arrayListOf(
                    Exercise("Pull Out", series = 4, reps = 6, isStatic = false, rest= 60),
                    Exercise("Pull Up", series = 4, reps = 8, isStatic = false, rest = 60),
                    Exercise("Body Row", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Bar Dip", series = 4, reps = 12, isStatic = false, rest = 60))),
                Workout(name = "Muscle Up - Level 3", exercises = arrayListOf(
                    Exercise("Muscle Up Lower", series = 4, reps = 10, isStatic = false, rest= 60),
                    Exercise("Pull Up", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Body Row", series = 4, reps = 25, isStatic = false, rest = 60),
                    Exercise("Bar Dip", series = 4, reps = 10, isStatic = false, rest = 25))),
                Workout(name = "Muscle Up - Level 4", exercises = arrayListOf(
                    Exercise("Jump Muscle Up", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Muscle Up Lower", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("Pull Up", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("Bar Dip", series = 6, reps = 12, isStatic = false, rest = 25))),
                Workout(name = "Muscle Up - Level 5", exercises = arrayListOf(
                    Exercise("Muscle Up", series = 3, reps = 5, isStatic = false, rest= 60),
                    Exercise("Jump Muscle Up", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("Pull Up", series = 3, reps = 10, isStatic = false, rest = 60),
                    Exercise("Bar Dip", series = 6, reps = 15, isStatic = false, rest = 25)))
            ),
            "Hefesto" to arrayListOf(
                Workout(name = "Hefesto - Level 1", exercises = arrayListOf(
                    Exercise("Back Lever", series = 3, reps = 10, isStatic = true, rest= 60),
                    Exercise("Hefesto Rowing", series = 4, reps = 10, rest = 60),
                    Exercise("Chin Up", series = 4, reps = 8, isStatic = false, rest = 60),
                    Exercise("Body Row", series = 3, reps = 12, isStatic = false, rest = 60))),
                Workout(name = "Hefesto - Level 2", exercises = arrayListOf(
                    Exercise("Headbangers", series = 6, reps = 10, rest = 60),
                    Exercise("Back Lever", series = 3, reps = 10, isStatic = true, rest= 60),
                    Exercise("Hefesto Rowing", series = 4, reps = 10, rest = 60),
                    Exercise("Chin Up", series = 3, reps = 8, isStatic = false, rest = 60))),
                Workout(name = "Hefesto - Level 3", exercises = arrayListOf(
                    Exercise("Hefesto Rowing", series = 6, reps = 10, rest= 60),
                    Exercise("Hefesto Lower", series = 4, reps = 6, rest = 60),
                    Exercise("Hefesto Hold", series = 4, reps = 10, isStatic = true, rest = 60),
                    Exercise("Back Lever", series = 3, reps = 12, isStatic = true, rest = 60))),
                Workout(name = "Hefesto - Level 4", exercises = arrayListOf(
                    Exercise("Tuck BL Pull Up", series = 6, reps = 10, rest = 60),
                    Exercise("Hefesto Rowing", series = 6, reps = 10, rest= 60),
                    Exercise("Hefesto Lower", series = 4, reps = 6, rest = 60),
                    Exercise("Back Lever", series = 3, reps = 12, isStatic = true, rest = 60))),
                Workout(name = "Hefesto - Level 5", exercises = arrayListOf(
                    Exercise("Hefesto", series = 3, reps = 10, rest= 60),
                    Exercise("Hefesto Lower", series = 4, reps = 6, rest = 60),
                    Exercise("Hefesto Rowing", series = 4, reps = 10, isStatic = false, rest = 60),
                    Exercise("Back Lever", series = 3, reps = 12, isStatic = true, rest = 60)))
            ),
            "Pistol Squat" to arrayListOf(
                Workout(name = "Pistol Squat - Level 1", exercises = arrayListOf(
                    Exercise("Squat", series = 4, reps = 25, isStatic = false, rest= 25),
                    Exercise("Lunge", series = 4, reps = 10, isStatic = false, rest = 60),
                    Exercise("Squat Hold", series = 3, reps = 60, isStatic = true, rest = 25),
                    Exercise("Squat", series = 6, reps = 12, isStatic = false, rest = 25))),
                Workout(name = "Pistol Squat - Level 2", exercises = arrayListOf(
                    Exercise("Jumping Squat", series = 3, reps = 15, isStatic = false, rest= 60),
                    Exercise("Squat", series = 4, reps = 25, isStatic = false, rest= 25),
                    Exercise("Lunge", series = 4, reps = 10, isStatic = false, rest = 25),
                    Exercise("Squat Hold", series = 3, reps = 60, isStatic = true, rest = 25))),
                Workout(name = "Pistol Squat - Level 3", exercises = arrayListOf(
                    Exercise("Assisted Pistol Squat", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Jumping Squat", series = 3, reps = 15, isStatic = false, rest= 25),
                    Exercise("Squat", series = 3, reps = 25, isStatic = false, rest= 25),
                    Exercise("Lunge", series = 4, reps = 15, isStatic = false, rest = 25))),
                Workout(name = "Pistol Squat - Level 4", exercises = arrayListOf(
                    Exercise("Pistol Squat Lower", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Assisted Pistol Squat", series = 3, reps = 8, isStatic = false, rest= 60),
                    Exercise("Jumping Squat", series = 3, reps = 15, isStatic = false, rest= 25),
                    Exercise("Lunge", series = 4, reps = 15, isStatic = false, rest = 25))),
                Workout(name = "Pistol Squat - Level 5", exercises = arrayListOf(
                    Exercise("Pistol Squat", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Pistol Squat Lower", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Assisted Pistol Squat", series = 3, reps = 8, isStatic = false, rest= 60),
                    Exercise("Jumping Squat", series = 3, reps = 15, isStatic = false, rest= 25)))
            ),
            "Planche" to arrayListOf(
                Workout(name = "Planche - Level 1", exercises = arrayListOf(
                    Exercise("Frog Stand", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Pseudo Push Up", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Lean Planche", series = 4, reps = 10, isStatic = true, rest = 60),
                    Exercise("Dynamic Lean Planche", series = 6, reps = 6, isStatic = false, rest = 25))),
                Workout(name = "Planche - Level 2", exercises = arrayListOf(
                    Exercise("Crow Stand", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Pseudo Push Up", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Lean Planche", series = 4, reps = 10, isStatic = true, rest = 60),
                    Exercise("Dynamic Lean Planche", series = 6, reps = 6, isStatic = false, rest = 25))),
                Workout(name = "Planche - Level 3", exercises = arrayListOf(
                    Exercise("Tuck Planche", series = 4, reps = 20, isStatic = true, rest= 60),
                    Exercise("Pseudo Push Up", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Lean Planche", series = 4, reps = 10, isStatic = true, rest = 60),
                    Exercise("Dynamic Lean Planche", series = 6, reps = 6, isStatic = false, rest = 25))),
                Workout(name = "Planche - Level 4", exercises = arrayListOf(
                    Exercise("Straddle Planche", series = 4, reps = 20, isStatic = true, rest= 60),
                    Exercise("Tuck Planche", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Pseudo Push Up", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Dynamic Lean Planche", series = 6, reps = 6, isStatic = false, rest = 25))),
                Workout(name = "Planche - Level 5", exercises = arrayListOf(
                    Exercise("Planche", series = 4, reps = 10, isStatic = true, rest= 60),
                    Exercise("Straddle Planche", series = 4, reps = 30, isStatic = true, rest= 60),
                    Exercise("Pseudo Push Up", series = 4, reps = 12, isStatic = false, rest = 60),
                    Exercise("Dynamic Lean Planche", series = 6, reps = 6, isStatic = false, rest = 25)))
            ),
            "Front Lever" to arrayListOf(
                Workout(name = "Front Lever - Level 1", exercises = arrayListOf(
                    Exercise("Tuck FL", series = 3, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck FL Raise", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("Pull Up", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("Leg Raise", series = 6, reps = 12, isStatic = false, rest = 25))),
                Workout(name = "Front Lever - Level 2", exercises = arrayListOf(
                    Exercise("Advanced Tuck FL", series = 3, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck FL", series = 3, reps = 30, isStatic = true, rest= 60),
                    Exercise("Tuck FL Raise", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("Toe To Bar", series = 3, reps = 8, isStatic = false, rest = 60))),
                Workout(name = "Front Lever - Level 3", exercises = arrayListOf(
                    Exercise("One Leg FL", series = 3, reps = 30, isStatic = true, rest= 60),
                    Exercise("Advanced Tuck FL", series = 3, reps = 20, isStatic = true, rest= 60),
                    Exercise("Ice Cream Maker", series = 4, reps = 10, isStatic = false, rest = 60),
                    Exercise("Toe To Bar", series = 3, reps = 12, isStatic = false, rest = 60))),
                Workout(name = "Front Lever - Level 4", exercises = arrayListOf(
                    Exercise("FL Lower", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("One Leg FL", series = 3, reps = 20, isStatic = true, rest= 60),
                    Exercise("Ice Cream Maker", series = 4, reps = 10, isStatic = false, rest = 60),
                    Exercise("Dragon Flag", series = 6, reps = 10, isStatic = false, rest = 60))),
                Workout(name = "Front Lever - Level 5", exercises = arrayListOf(
                    Exercise("Front Lever", series = 3, reps = 10, isStatic = true, rest= 60),
                    Exercise("FL Lower", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("One Leg FL", series = 3, reps = 10, isStatic = true, rest = 60),
                    Exercise("Dragon Flag", series = 6, reps = 10, isStatic = false, rest = 25)))
            ),
            "Back Lever" to arrayListOf(
                Workout(name = "Back Lever - Level 1", exercises = arrayListOf(
                    Exercise("Skin The Cat", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Toe To Bar", series = 3, reps = 10, isStatic = false, rest = 60),
                    Exercise("Lumbar Extension", series = 4, reps = 15, isStatic = false, rest = 25),
                    Exercise("Plank", series = 3, reps = 60, isStatic = true, rest = 25))),
                Workout(name = "Back Lever - Level 2", exercises = arrayListOf(
                    Exercise("Tuck BL", series = 3, reps = 30, isStatic = true, rest= 60),
                    Exercise("Skin The Cat", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Toe To Bar", series = 3, reps = 10, isStatic = false, rest = 60),
                    Exercise("Lumbar Extension", series = 4, reps = 15, isStatic = false, rest = 25))),
                Workout(name = "Back Lever - Level 3", exercises = arrayListOf(
                    Exercise("Advanced BL", series = 3, reps = 25, isStatic = true, rest= 60),
                    Exercise("Tuck BL", series = 3, reps = 10, isStatic = true, rest= 60),
                    Exercise("Skin The Cat", series = 3, reps = 10, isStatic = false, rest= 60),
                    Exercise("Advanced FL", series = 3, reps = 10, isStatic = false, rest = 60))),
                Workout(name = "Back Lever - Level 4", exercises = arrayListOf(
                    Exercise("One Leg BL", series = 4, reps = 20, isStatic = true, rest= 60),
                    Exercise("BL Lower", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("Advanced BL", series = 3, reps = 15, isStatic = true, rest= 60),
                    Exercise("Skin The Cat", series = 3, reps = 10, isStatic = false, rest= 60))),
                Workout(name = "Back Lever - Level 5", exercises = arrayListOf(
                    Exercise("Back Lever", series = 3, reps = 10, isStatic = true, rest= 60),
                    Exercise("One Leg BL", series = 3, reps = 15, isStatic = true, rest= 60),
                    Exercise("BL Lower", series = 3, reps = 8, isStatic = false, rest = 60),
                    Exercise("One Leg FL", series = 4, reps = 15, isStatic = true, rest = 60)))
            )

        )

    }
}