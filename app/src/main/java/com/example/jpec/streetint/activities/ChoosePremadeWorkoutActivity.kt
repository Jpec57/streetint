package com.example.jpec.streetint.activities

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChoosePremadeWorkoutAdapter
import com.example.jpec.streetint.interfaces.ChoosePremadeWorkoutListener
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.activity_choose_premade_workout.*
import android.app.Dialog
import android.view.Window
import com.example.jpec.streetint.adapters.DialogChoosePremadeAdapter
import com.example.jpec.streetint.Constants.HardcodedWorkouts


class ChoosePremadeWorkoutActivity : Activity(), ChoosePremadeWorkoutListener {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var allWorkouts: MutableList<Workout>

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
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_choose_premade_workout)

        val dialogViewManager = LinearLayoutManager(dialog.context)
        val dialogViewAdapter = DialogChoosePremadeAdapter()
        val dialogRecyclerView = dialog.findViewById<RecyclerView>(R.id.dialog_recycler).apply {
            setHasFixedSize(true)
            layoutManager = dialogViewManager
            adapter = dialogViewAdapter
        }
        dialogRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                recyclerView.context,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
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