package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChoosePremadeWorkoutAdapter
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.activity_choose_premade_workout.*
import android.app.Dialog
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.jpec.streetint.adapters.DialogChoosePremadeAdapter
import com.example.jpec.streetint.constants.Constants
import com.example.jpec.streetint.constants.HardcodedWorkouts


class ChoosePremadeWorkoutActivity : Activity() {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: ChoosePremadeWorkoutAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var allWorkouts: MutableList<Workout>
    private var selectedMuscles = arrayListOf("All")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_premade_workout)

        setOnClickButtons()
        initWorkouts()

        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        viewAdapter = ChoosePremadeWorkoutAdapter(this, allWorkouts)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                recyclerView.context,
                androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
            )
        )
    }

    private fun setOnClickButtons()
    {
        filter.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_choose_premade_workout)

        val windowManager = WindowManager.LayoutParams()
        windowManager.width = WindowManager.LayoutParams.MATCH_PARENT
        windowManager.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = windowManager


        val dialogViewManager = LinearLayoutManager(dialog.context)
        val dialogViewAdapter = DialogChoosePremadeAdapter(Constants.extendedMuscleList, selectedMuscles)
        val dialogRecyclerView = dialog.findViewById<RecyclerView>(R.id.dialog_recycler).apply {
            setHasFixedSize(true)
            layoutManager = dialogViewManager
            adapter = dialogViewAdapter
        }
        dialog.setOnDismissListener {
            selectedMuscles = dialogViewAdapter.getSelectedMuscles()
            var muscles = ""
            for (m in selectedMuscles)
                muscles += "$m,"
            Log.e("HELIX", muscles.dropLast(1))

            viewAdapter.filter.filter(muscles.dropLast(1))
        }
/*
        val text = dialog.findViewById(R.id.text_dialog) as TextView
        text.text = msg

        val dialogButton = dialog.findViewById(R.id.btn_dialog) as Button
        dialogButton.setOnClickListener(object : View.OnClickListener() {
            fun onClick(v: View) {
                dialog.dismiss()
            }
        })
        */
        dialog.show()

    }

    private fun initWorkouts()
    {
        allWorkouts = HardcodedWorkouts.premadeWorkouts

    }
}