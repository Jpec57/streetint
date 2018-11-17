package com.example.jpec.streetint.adapters

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.expandable_parent_random_workout.view.*
import java.lang.Exception

class RandomWorkoutAdapter(val context: Context, private val choices: MutableMap<String, ArrayList<String>>?) : RecyclerView.Adapter<RandomWorkoutAdapter.MyViewHolder>()
{
    internal val selectedMap = mutableMapOf<String, Boolean>()
    private lateinit var parentView: ViewGroup


    class MyViewHolder(val baseLayout: ConstraintLayout) : RecyclerView.ViewHolder(baseLayout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RandomWorkoutAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.expandable_parent_random_workout, parent, false) as ConstraintLayout
        for (key in choices!!.keys)
        {
            for (item in choices[key]!!)
            {
                selectedMap[item] = false
            }
        }
        parentView = parent
        return MyViewHolder(baseLayout)
    }
    override fun getItemCount() = choices?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.baseLayout.option_name.text = choices!!.keys.elementAt(position)

        val viewManager = LinearLayoutManager(context)
        val viewAdapter = RandomWorkoutInsideAdapter(holder.baseLayout.selected, choices[choices.keys.elementAt(position)],
            choices!!.keys.elementAt(position), this, position > 1)

        val recycler = holder.baseLayout.recyclerInside
        recycler.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        holder.baseLayout.expand.setOnClickListener {
            holder.baseLayout.recyclerInside.visibility = if (holder.baseLayout.recyclerInside.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }

    fun setPreviewTextAtPos(textView: TextView, position: String)
    {
        var string = ""
        for (item in choices!![position]!!)
        {
            if (selectedMap[item]!!)
            {
                string += " $item,"
            }
        }
        string = if (string.length > 1) string.substring(0, string.length - 1) else string
        textView.text = string
    }

    fun setSelectedAtPos(item: String)
    {
        val previousVal = selectedMap[item] as Boolean
        selectedMap[item] = !previousVal
    }
}