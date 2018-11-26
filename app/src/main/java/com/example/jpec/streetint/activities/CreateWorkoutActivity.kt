package com.example.jpec.streetint.activities

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.activity_create_workout.*

class CreateWorkoutActivity : AppCompatActivity() {
    private val muscleColorMap = mapOf("chest" to R.style.chest,
        "biceps" to R.style.biceps, "triceps" to R.style.triceps,
        "leg" to R.style.leg, "shoulder" to R.style.shoulders,
        "trapezius" to R.style.trapezius, "back" to R.style.back,
        "abs" to R.style.abs)
    private val selectedMuscles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        setOnClickButtons()
    }


    private fun setMuscleFilters(muscle : String)
    {
        if (muscle != "penis")
        {
            if (selectedMuscles.contains(muscle))
                selectedMuscles.remove(muscle)
            else
                selectedMuscles.add(muscle)
            val theme = resources.newTheme()
            for (m in selectedMuscles)
                theme.applyStyle(muscleColorMap[m]!!, false)

            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_paths, theme)
            svg.setImageDrawable(drawable)
        }
        else
            showToast("Wtf are you doing here ?")
    }

    private fun setOnClickButtons()
    {
        val onClick = View.OnClickListener {
            it as Button
            showToast(it.text.toString())
            setMuscleFilters(it.text.toString())
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



    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}