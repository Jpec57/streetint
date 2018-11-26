package com.example.jpec.streetint.adapters

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.adapter_show_exercise_content.view.*

class ShowWorkoutContentAdapter(private val context: Context,private val w: Workout) : androidx.recyclerview.widget.RecyclerView.Adapter<ShowWorkoutContentAdapter.MyViewHolder>()
{
    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ShowWorkoutContentAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_show_exercise_content, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }
    override fun getItemCount() = w.exercises.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val exo = w.exercises[position]
        val reps = "${exo.reps} REPS"
        val series = "${exo.series} SERIES"
        val weight = "+ ${exo.weight} KGS"
        val tempo = when (exo.tempo)
        {
            1 -> "QUICK"
            2-> "NORMAL"
            else -> "SLOW"
        }
        val rest = if (exo.rest > 60) "${exo.rest / 60}min${exo.rest % 60}"  else "${exo.rest}sec"
        holder.baseLayout.exo_name.text = exo.name
        holder.baseLayout.exo_reps.text = reps
        holder.baseLayout.exo_series.text = series
        holder.baseLayout.exo_weight.text = weight
        holder.baseLayout.exo_tempo.text = tempo
        holder.baseLayout.exo_rest.text = rest



//        holder.baseLayout.workout_name.text = allWorkouts[position].name
    }
}