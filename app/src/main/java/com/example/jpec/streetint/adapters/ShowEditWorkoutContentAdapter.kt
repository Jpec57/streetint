package com.example.jpec.streetint.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jpec.streetint.R
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.adapter_show_exercise_content.view.*


class ShowEditWorkoutContentAdapter(private val w: Workout, private val communicator: CreateWorkoutCommunicator) : androidx.recyclerview.widget.RecyclerView.Adapter<ShowEditWorkoutContentAdapter.MyViewHolder>()
{
    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ShowEditWorkoutContentAdapter.MyViewHolder {
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
        holder.baseLayout.reorder.visibility = View.VISIBLE
    }
}