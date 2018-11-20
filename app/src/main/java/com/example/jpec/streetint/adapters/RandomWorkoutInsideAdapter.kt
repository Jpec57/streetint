package com.example.jpec.streetint.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.expandable_child_random_workout.view.*

class RandomWorkoutInsideAdapter(val textView: TextView, private val choices: List<String>?,
                                 private val optionsName: String, private val randomWorkoutAdapter: RandomWorkoutAdapter,
                                 private val multiple: Boolean) : RecyclerView.Adapter<RandomWorkoutInsideAdapter.MyViewHolder>()
{
    private var oldSelected : Int = -1
    private var oldSelectedView: View? = null
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
        if (randomWorkoutAdapter.selectedMap[choices[position]]!!)
        {
            holder.baseLayout.chosen.visibility = View.VISIBLE
            oldSelectedView = holder.baseLayout.chosen
            oldSelected = position
        }
        holder.baseLayout.option_layout.setOnClickListener {
            if (!multiple)
            {
                if (oldSelected != -1)
                {
                    randomWorkoutAdapter.setSelectedAtPos(choices[oldSelected])
                    oldSelectedView!!.visibility = View.INVISIBLE

                }
            }
            if (holder.baseLayout.chosen.visibility == View.VISIBLE)
                holder.baseLayout.chosen.visibility = View.INVISIBLE
            else
                holder.baseLayout.chosen.visibility = View.VISIBLE
            if (!multiple)
            {
                oldSelected = position
                oldSelectedView = holder.baseLayout.chosen
            }
            randomWorkoutAdapter.setSelectedAtPos(choices[position])
            randomWorkoutAdapter.setPreviewTextAtPos(textView, optionsName)
        }
    }
}