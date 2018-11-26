package com.example.jpec.streetint.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.InWorkoutActivity
import com.example.jpec.streetint.activities.ShowWorkoutContentActivity
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.adapter_choose_skill_progression_lvl.view.*

class ChooseSkillProgressionAdapter(val context: Context, val workouts : ArrayList<Workout>, val lvl : Int) :  androidx.recyclerview.widget.RecyclerView.Adapter<ChooseSkillProgressionAdapter.MyViewHolder>()
{

    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChooseSkillProgressionAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_choose_skill_progression_lvl, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }
    override fun getItemCount() = workouts.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val e = workouts[position].exercises
        holder.baseLayout.header.setOnClickListener {
            if (holder.baseLayout.content.visibility == View.GONE)
                holder.baseLayout.content.visibility = View.VISIBLE
            else
                holder.baseLayout.content.visibility = View.GONE
            holder.baseLayout.start.setOnClickListener {
                val intent = Intent(context, ShowWorkoutContentActivity::class.java)
                context.startActivity(intent)
            }
        }
        holder.baseLayout.progressName.text = workouts[position].name
        val goal = "Goal: ${e[0].reps} reps/sec"
        holder.baseLayout.goal.text = goal
        val first = "${e[0].series} * ${e[0].reps}"
        holder.baseLayout.firstExo.text = first
        val second = "${e[1].series} * ${e[1].reps}"
        holder.baseLayout.secondExo.text = second
        val third = "${e[2].series} * ${e[2].reps}"
        holder.baseLayout.thirdExo.text = third
        val fourth = "${e[3].series} * ${e[3].reps}"
        holder.baseLayout.fourthExo.text = fourth
        holder.baseLayout.firstExoName.text = "${e[0].name}"
        holder.baseLayout.secondExoName.text = "${e[1].name}"
        holder.baseLayout.thirdExoName.text = "${e[2].name}"
        holder.baseLayout.fourthExoName.text = "${e[3].name}"
        holder.baseLayout.progressNumber.text = "${position + 1}"
        if (lvl >= position + 1)
        {
            holder.baseLayout.start.setOnClickListener {
                val intent = Intent(context, InWorkoutActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("workout", workouts[position])
                intent.putExtras(bundle)
                intent.putExtra("skillName", workouts[position].name.substringBeforeLast('-').dropLast(1))
                intent.putExtra("type", 1)
                intent.putExtra("goal", e[0].reps * e[0].series)
                context.startActivity(intent)
            }
        }
        else
        {
            holder.baseLayout.goal.text = context.getString(R.string.level_up_first)
            holder.baseLayout.content.setBackgroundColor(context.resources.getColor(R.color.greyBlack))
        }
    }
}