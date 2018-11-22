package com.example.jpec.streetint.adapters

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.ShowWorkoutContentActivity
import kotlinx.android.synthetic.main.adapter_choose_skill_progression_lvl.view.*

class ChooseSkillProgressionAdapter(val context: Context) :  RecyclerView.Adapter<ChooseSkillProgressionAdapter.MyViewHolder>()
{

    class MyViewHolder(val baseLayout: ConstraintLayout) : RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChooseSkillProgressionAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_choose_skill_progression_lvl, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }
    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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


//        holder.baseLayout.option_name.text = choices!!.keys.elementAt(position)
    }
}