package com.example.jpec.streetint.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.CreateWorkoutActivity
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import com.example.jpec.streetint.models.Exercise
import kotlinx.android.synthetic.main.adapter_choose_exo.view.*

class ChooseExoAdapter(private val exoList: ArrayList<Exercise>, private val communicator : CreateWorkoutCommunicator) : androidx.recyclerview.widget.RecyclerView.Adapter<ChooseExoAdapter.MyViewHolder>(), Filterable {
    private var exoListFiltered = exoList

    override fun getFilter() =
        object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString()
                if (query.isEmpty())
                    exoListFiltered = exoList
                else
                {
                    exoListFiltered = ArrayList()
                    for (exo in exoList)
                        if (exo.name.contains(query, ignoreCase = true))
                            exoListFiltered.add(exo)
                }
                val filterResults = FilterResults()
                filterResults.values = exoListFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }


    override fun getItemCount() = exoListFiltered.size

    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChooseExoAdapter.MyViewHolder {

        val baseLayout = LayoutInflater.from(parent.context)
        .inflate(R.layout.adapter_choose_exo, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.baseLayout.exo_name.text = exoListFiltered[position].name
        holder.baseLayout.exo_muscle.text = exoListFiltered[position].muscles.toString()
        holder.baseLayout.exo_desc.text = exoListFiltered[position].description
        holder.baseLayout.setOnClickListener {
            communicator.addExercise(exoListFiltered[position])
        }
    }


}