package com.example.jpec.streetint.fragments.main_activity.end_of_workout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.EndOfWorkoutActivity
import com.example.jpec.streetint.adapters.EndOfWorkoutContentAdapter
import com.example.jpec.streetint.models.Exercise
import com.example.jpec.streetint.models.Workout
import kotlinx.android.synthetic.main.fragment_end_of_workout_content.*
import java.lang.Exception
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint


class EndOfWorkoutContentFragment : Fragment() {
    private lateinit var ref: EndOfWorkoutActivity
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var workouts: List<Workout>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_end_of_workout_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ref = this.activity as EndOfWorkoutActivity
        setWorkoutAdapter(view)
        setChart()
        setOnClickButtons()
    }

    private fun setWorkoutAdapter(view: View)
    {
        workouts = ref.workouts
        viewManager = LinearLayoutManager(activity!!.applicationContext)
        viewAdapter = EndOfWorkoutContentAdapter(activity!!.applicationContext, workouts)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.HORIZONTAL))
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