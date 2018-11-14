package com.example.jpec.streetint.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.expandable_child_random_workout.view.*

class RandomWorkoutInsideAdapter(private val choices: List<String>?) : RecyclerView.Adapter<RandomWorkoutInsideAdapter.MyViewHolder>()
{
    class MyViewHolder(val baseLayout: ConstraintLayout) : RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RandomWorkoutInsideAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.expandable_child_random_workout, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }
    override fun getItemCount() = choices?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.baseLayout.options.text = choices!![position]
        holder.baseLayout.option_layout.setOnClickListener {
            if (holder.baseLayout.chosen.visibility == View.VISIBLE)
            {
                holder.baseLayout.chosen.visibility = View.INVISIBLE
            }
            else
            {
                holder.baseLayout.chosen.visibility = View.VISIBLE
            }
        }
    }
}