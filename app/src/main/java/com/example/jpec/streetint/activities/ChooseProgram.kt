package com.example.jpec.streetint.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.activity_choose_program.*

class ChooseProgram : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_program)
        setOnClickButtons()
    }

    private fun setOnClickButtons()
    {
        premade.setOnClickListener { startActivity(Intent(this, ChoosePremadeWorkout::class.java)) }
    }

}