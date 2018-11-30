package com.example.jpec.streetint.fragments.create_workout

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ShowEditWorkoutContentAdapter
import com.example.jpec.streetint.adapters.ShowWorkoutContentAdapter
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import com.example.jpec.streetint.interfaces.OnStartDragListener
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout

class ResumeWorkoutFragment : androidx.fragment.app.Fragment(), OnStartDragListener {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var communicator: CreateWorkoutCommunicator

    val workout = Workout(name = "Test", exercises = arrayListOf(Exercise("testA"), Exercise("testB"), Exercise("testC")))


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_show_workout_content, container, false)
        Log.e("HELIX", "workout size ${workout.exercises.size}")
        viewManager = LinearLayoutManager(activity!!)
        viewAdapter = ShowEditWorkoutContentAdapter(workout, activity as CreateWorkoutCommunicator, this)

        recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        communicator = activity as CreateWorkoutCommunicator

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser)
        {
        }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {

    }
}