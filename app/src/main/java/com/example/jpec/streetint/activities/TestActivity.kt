package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.example.jpec.streetint.R


class TestActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }



    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}