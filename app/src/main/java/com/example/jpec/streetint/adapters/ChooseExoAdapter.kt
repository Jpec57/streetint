package com.example.jpec.streetint.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.CreateWorkoutActivity
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import com.example.jpec.streetint.models.Exercise
import kotlinx.android.synthetic.main.adapter_choose_exo.view.*

class ChooseExoAdapter(private val exoList: ArrayList<Exercise>, private val communicator : CreateWorkoutCommunicator) : androidx.recyclerview.widget.RecyclerView.Adapter<ChooseExoAdapter.MyViewHolder>() {

    override fun getItemCount() = exoList.size

    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChooseExoAdapter.MyViewHolder {

        val baseLayout = LayoutInflater.from(parent.context)
        .inflate(R.layout.adapter_choose_exo, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.baseLayout.exo_name.text = exoList[position].name
        holder.baseLayout.exo_muscle.text = exoList[position].muscles.toString()
        holder.baseLayout.exo_desc.text = exoList[position].description
        holder.baseLayout.setOnClickListener {
            communicator.addExercise(exoList[position])
        }
    }
}