package com.example.jpec.streetint.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.activity_your_workouts.*

class YourWorkoutsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_workouts)
        setOnClickButtons()
    }

    private fun setOnClickButtons()
    {
        create.setOnClickListener {
            startActivity(Intent(this, CreateWorkoutActivity::class.java))
        }
        random.setOnClickListener {
            startActivity(Intent(this, RandomWorkoutActivity::class.java))
        }
        tempo.setOnClickListener {
            Toast.makeText(this, "Not available yet", Toast.LENGTH_LONG).show()
//            startActivity(Intent(this, TempoWorkoutActivity::class.java))
        }
        created_workouts.setOnClickListener {
            startActivity(Intent(this, CreatedWorkoutActivity::class.java))
        }
    }
}