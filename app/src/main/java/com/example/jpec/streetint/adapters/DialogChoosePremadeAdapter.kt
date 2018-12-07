package com.example.jpec.streetint.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.ShowWorkoutContentActivity

class DialogChoosePremadeAdapter :androidx.recyclerview.widget.RecyclerView.Adapter<DialogChoosePremadeAdapter.MyViewHolder>()  {

    class MyViewHolder(val baseLayout: ConstraintLayout) : androidx.recyclerview.widget.RecyclerView.ViewHolder(baseLayout)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DialogChoosePremadeAdapter.MyViewHolder {
        val baseLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.dialog_adapter_choose_premade, parent, false) as ConstraintLayout
        return MyViewHolder(baseLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount() = 5
}