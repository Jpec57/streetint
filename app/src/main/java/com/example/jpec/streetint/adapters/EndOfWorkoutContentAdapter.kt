package com.example.jpec.streetint.adapters

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.adapter_end_of_workout_exercises.view.*
import java.lang.IndexOutOfBoundsException


class EndOfWorkoutContentAdapter(private val context: Context, private val w: List<Workout>?) : RecyclerView.Adapter<EndOfWorkoutContentAdapter.MyViewHolder>()
{
    class MyViewHolder(val baseLayout: ConstraintLayout) : RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): EndOfWorkoutContentAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_end_of_workout_exercises, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }
    override fun getItemCount() = if (w != null ) w[w.size - 1].exercises.size else 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val exo = w!![w.size - 1].exercises[position]
        val exoRep = "${exo.reps} Reps"
        val exoRepMoy = "${exo.reps / exo.series} reps/set"
        var previousNbReps : Float
        previousNbReps = try {
            w[w.size - 2].exercises[position].reps.toFloat()
        }catch (i : IndexOutOfBoundsException) {
            exo.reps.toFloat()
        }
        val percent : Int = (exo.reps / previousNbReps * 100).toInt() - 100
        val percentString = if (percent > 0) "+$percent%" else "$percent%"
        holder.baseLayout.percent.text = percentString
        holder.baseLayout.isPositive.setBackgroundColor(if (percent > 0) ContextCompat.getColor(context, R.color.green)  else ContextCompat.getColor(context, R.color.red))
        holder.baseLayout.exo_name.text = exo.name
        holder.baseLayout.exo_reps.text = exoRep
        holder.baseLayout.exo_reps_moy.text = exoRepMoy
    }
}