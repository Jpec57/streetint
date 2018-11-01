package com.example.jpec.streetint.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.adapter_choose_premade.view.*

class ChoosePremadeWorkoutAdapter(private val context: Context, private val myDataset: Array<String>) :
    RecyclerView.Adapter<ChoosePremadeWorkoutAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val baseLayout: ConstraintLayout) : RecyclerView.ViewHolder(baseLayout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChoosePremadeWorkoutAdapter.MyViewHolder {
        // create a new view
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_choose_premade, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(baseLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.baseLayout.workout_name.text = myDataset[position]
        holder.baseLayout.setOnClickListener { Toast.makeText(context, myDataset[position], Toast.LENGTH_SHORT).show() }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}