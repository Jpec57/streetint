package com.example.jpec.streetint.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.ShowWorkoutContentActivity
import com.example.jpec.streetint.models.Workout
import com.google.common.collect.Lists
import kotlinx.android.synthetic.main.adapter_choose_premade.view.*
import java.util.*

class ChoosePremadeWorkoutAdapter(private val context: Context, private val allWorkouts: MutableList<Workout>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ChoosePremadeWorkoutAdapter.MyViewHolder>(), Filterable {

    private fun getCorrespondingWorkouts(muscles : ArrayList<String>) : ArrayList<Workout>
    {
        Log.e("HELIX", "ARR: $muscles")
        val arr = ArrayList<Workout>()
        for (w in allWorkouts)
        {
            for (m in w.muscles)
            {
                if (muscles.contains(m))
                    arr.add(w)
            }
        }
        return arr
    }

    override fun getFilter() = object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val arr = Lists.newArrayList<String>(constraint.toString().split(','))
            Log.e("HELIX", "")
            val filterResults = FilterResults()
            filterResults.values = getCorrespondingWorkouts(arr)
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            notifyDataSetChanged()
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

    private fun setTime(duration: Int) : String
    {
        return when {
            duration > 3600 -> "${duration / 3600} hr ${(duration % 3600) / 60} min"
            duration > 60 -> "${duration / 60} min"
            else -> "$duration sec"
        }
    }
}