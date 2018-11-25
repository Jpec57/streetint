package com.example.jpec.streetint.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.ChooseSkillProgressionLvlActivity
import com.example.jpec.streetint.models.ProfileDataModel
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.adapter_choose_skill.view.*

class ChooseSkillAdapter(val context: Context, private val allSkills: MutableMap<String, ArrayList<Workout>>, private val skillLevels: ProfileDataModel.SkillLevels) :  RecyclerView.Adapter<ChooseSkillAdapter.MyViewHolder>()
{
    private val skillNames = allSkills.keys

    class MyViewHolder(val baseLayout: ConstraintLayout) : RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChooseSkillAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_choose_skill, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }
    override fun getItemCount() = allSkills.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val key = skillNames.elementAt(position)
        holder.baseLayout.skillName.text = key
        val skillLvl = "- lvl ${skillLevels.skillLevels[key]} -"
        holder.baseLayout.lvl.text = skillLvl
        holder.baseLayout.chooseSkill.setOnClickListener {
            val intent = Intent(context, ChooseSkillProgressionLvlActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("workouts", allSkills[key])
            intent.putExtra("lvl", skillLevels.skillLevels[key])
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}