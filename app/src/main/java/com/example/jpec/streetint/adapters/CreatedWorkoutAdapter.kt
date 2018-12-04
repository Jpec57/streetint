package com.example.jpec.streetint.adapters

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.adapter_created_workout_overview.view.*

class CreatedWorkoutAdapter(val context : Context, val workouts : List<Workout>): androidx.recyclerview.widget.RecyclerView.Adapter<CreatedWorkoutAdapter.MyViewHolder>() {

    override fun getItemCount() = workouts.size + 2

    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)

    companion object {
        const val BLANK_VIEW = 0
        const val NORMAL_VIEW = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(position)
        {
            0 -> BLANK_VIEW
            workouts.size + 1 -> BLANK_VIEW
            else -> NORMAL_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CreatedWorkoutAdapter.MyViewHolder {

        val baseLayout = when(viewType)
        {
            BLANK_VIEW -> LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_created_workout_empty, parent, false) as ConstraintLayout
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_created_workout_overview, parent, false) as ConstraintLayout
        }
        return MyViewHolder(baseLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == 0 || position == workouts.size + 1)
        {

        }
        else
        {
            holder.baseLayout.number.text = "${position - 1}"
            holder.baseLayout.workoutName.text = workouts[position - 1].name
            holder.baseLayout.container.setOnClickListener {
                Toast.makeText(context, "WORKOUT: ${workouts[position - 1].name}", Toast.LENGTH_LONG).show()
            }

        }
    }
}