package com.example.jpec.streetint.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.ShowWorkoutContentActivity
import com.example.jpec.streetint.models.StringList
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.adapter_choose_premade.view.*

class ChoosePremadeWorkoutAdapter(private val context: Context, private val allWorkouts: MutableList<Workout>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ChoosePremadeWorkoutAdapter.MyViewHolder>(), Filterable {
    override fun getFilter() = object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
//            filterResults.values = exoListFiltered
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

        }
    }

    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChoosePremadeWorkoutAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_choose_premade, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.baseLayout.workout_name.text = allWorkouts[position].name
        holder.baseLayout.workout_muscle.text = setMaterial(allWorkouts[position].material)
        holder.baseLayout.time.text = setTime(allWorkouts[position].time)
        holder.baseLayout.setOnClickListener {
            val intent = Intent(context, ShowWorkoutContentActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("workout", allWorkouts[position])
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = allWorkouts.size

    private fun setMaterial(tab: MutableList<String>) : String
    {
        var material = ""
        if (tab.size == 1 && tab[0] == "None")
            material = "None"
        else
        {
            tab.remove("None")
            for (m in tab)
                material += ("$m\n")
        }
        return material
    }

    private fun setMaterial(tab: StringList) : String
    {
        var material = ""
        if (tab.list.size == 1 && tab.list[0] == "None")
            material = "None"
        else
        {
            tab.list.remove("None")
            for (m in tab.list)
                material += ("$m\n")
        }
        return material
    }

    private fun setTime(duration: Int) : String
    {
        lateinit var res : String
        if (duration > 3600)
        {
            res = "${duration / 3600} hr ${(duration % 3600) / 60} min"
        }
        else if (duration > 60)
        {
            res = "${duration / 60} min"
        }
        else
        {
            res = "$duration sec"
        }
        return res
    }
}