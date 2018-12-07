package com.example.jpec.streetint.fragments.endOfWorkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.EndOfWorkoutActivity
import com.example.jpec.streetint.adapters.EndOfWorkoutContentAdapter
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.fragment_end_of_workout_content.*
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.DataPoint


class EndOfWorkoutContentFragment : androidx.fragment.app.Fragment() {
    private lateinit var ref: EndOfWorkoutActivity
    private var recyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>? = null
    private var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager? = null
    private var workouts: List<Workout>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_end_of_workout_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ref = this.activity as EndOfWorkoutActivity
        ref.contextContentFragment = this
        setOnClickButtons()
    }

    fun onWorkoutRetrieve() {
        setWorkoutAdapter()
        setChart()
    }

    private fun setWorkoutAdapter()
    {
        workouts = ref.workouts
        viewManager = androidx.recyclerview.widget.LinearLayoutManager(activity!!.applicationContext)
        viewAdapter = EndOfWorkoutContentAdapter(activity!!.applicationContext, workouts)

        recyclerView = view!!.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun setOnClickButtons()
    {
        chart.setOnClickListener {
            chart_layout.visibility = View.VISIBLE
            exos_layout.visibility = View.INVISIBLE
        }
        exos.setOnClickListener {
            chart_layout.visibility = View.INVISIBLE
            exos_layout.visibility = View.VISIBLE
        }
    }

    private fun getRepsSum(graphPoint: LineGraphSeries<DataPoint>)
    {
        var i = 0.0
        for (w in workouts!!)
        {
            var s = 0
            for (e in w.exercises)
                s += e.reps
            graphPoint.appendData(DataPoint(i, s.toDouble()), true, 10)
            i += 1
        }
    }

    private fun setChart()
    {
        var string = ""
        workouts?.let {
            for (w in it)
            {
                string += "------------------------\n"
                string += "${w.timestamp}\n"
                string += "------------------------\n"
                string += "${w.exercises[0].name} ${w.exercises[0].reps}\n"
//                string += "${w.exercises[1].name} ${w.exercises[1].reps}\n"
                string += "\n"
            }
        }
//        summary.text = string
        val series = LineGraphSeries<DataPoint>()
        getRepsSum(series)
        graph.addSeries(series)
    }
}