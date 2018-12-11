package com.example.jpec.streetint.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.dialog_adapter_choose_premade.view.*

class DialogChoosePremadeAdapter(private val muscleList: ArrayList<String>,
                                 private val oldSelectedMuscles: ArrayList<String>)
    : androidx.recyclerview.widget.RecyclerView.Adapter<DialogChoosePremadeAdapter.MyViewHolder>(){
    fun getSelectedMuscles() = selectedMuscles

    private var selectedMuscles = ArrayList<String>()

    class MyViewHolder(val baseLayout: LinearLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DialogChoosePremadeAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.dialog_adapter_choose_premade, parent, false) as LinearLayout
        return MyViewHolder(baseLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.baseLayout.muscleText.text = muscleList[position]
        holder.baseLayout.muscleText.setOnClickListener {
            holder.baseLayout.checkbox.performClick()
        }
        if (oldSelectedMuscles.contains(muscleList[position]))
            holder.baseLayout.checkbox.performClick()
        holder.baseLayout.checkbox.setOnClickListener {
            if ((it as CheckBox).isChecked)
                selectedMuscles.add(muscleList[position])
            else
                selectedMuscles.remove(muscleList[position])
        }

    }

    override fun getItemCount() = muscleList.size
}