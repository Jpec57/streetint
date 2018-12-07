package com.example.jpec.streetint.fragments.createWorkout

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ShowEditWorkoutContentAdapter
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import com.example.jpec.streetint.models.Workout
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.jpec.streetint.activities.InWorkoutActivity
import com.example.jpec.streetint.databases.DbWorkerThread
import com.example.jpec.streetint.databases.WorkoutDatabase
import kotlinx.android.synthetic.main.activity_show_workout_content.*
import org.jetbrains.anko.support.v4.toast
import java.lang.NullPointerException
import java.util.*


class ResumeWorkoutFragment : androidx.fragment.app.Fragment() {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var communicator: CreateWorkoutCommunicator

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var mDb: WorkoutDatabase? = null
    private var mUiHandler = Handler()
    private var already = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_show_workout_content, container, false)
        setAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickButtons()
        setDatabase()
        testIfWorkoutInDb(toInsert = false)
    }

    override fun onDestroy() {
        super.onDestroy()
        WorkoutDatabase.destroyInstance()
        mDbWorkerThread.quit()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        communicator = activity as CreateWorkoutCommunicator

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser)
        {
            setAdapter()
        }
    }

    private fun setDatabase()
    {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = WorkoutDatabase.getInstance(activity!!.applicationContext)
    }

    private fun insertWorkoutInDb(workout: Workout)
    {
        val task = Runnable {
            mDb?.workoutDao()?.insertWorkout(workout)
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun testIfWorkoutInDb(toInsert: Boolean)
    {
        val workout = communicator.getWorkout()
        workout.name = workout_name.text.toString()
        communicator.setWorkout(workout)
        val task = Runnable {
            val w = mDb?.workoutDao()?.getInitWorkout(workout.name)
            already = if(w != null) {
                save.setImageResource(android.R.drawable.btn_star_big_on)
                true
            } else {
                save.setImageResource(android.R.drawable.btn_star_big_off)
                false
            }
            if (toInsert)
            {
                mUiHandler.post{
                    if (already)
                    {
                        activity?.let {
                            val builder = AlertDialog.Builder(it)
                            builder.setMessage("Are you sure you want to overwrite your workout ?")
                                .setPositiveButton(R.string.yes) { _, _ ->
                                    insertWorkoutInDb(workout)
                                }
                                .setNegativeButton(R.string.no) { dialog, _ ->
                                    dialog.dismiss()
                                }
                            builder.create()
                        } ?: throw IllegalStateException("Activity cannot be null")
                    }
                    else
                        insertWorkoutInDb(workout)
                }
            }
        }
        while (!mDbWorkerThread.ready) ;
        mDbWorkerThread.postTask(task)
    }

    private fun setOnClickButtons()
    {
        save.setOnClickListener {
            if (workout_name.text.toString() == activity!!.resources.getString(R.string.workout_name))
                toast("You have to change the name of the workout first")
            else
                testIfWorkoutInDb(toInsert = true)
        }
        start.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("workout", communicator.getWorkout())
            val intent = Intent(activity, InWorkoutActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setAdapter()
    {
        try {
            edit.setOnClickListener {
                communicator.goBackToMuscle()
            }
            viewManager = LinearLayoutManager(activity!!)
            viewAdapter = ShowEditWorkoutContentAdapter(communicator.getWorkout(), activity as CreateWorkoutCommunicator)
            recyclerView = activity!!.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }

            ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP, ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val workout = communicator.getWorkout()
                    Collections.swap(workout.exercises, viewHolder.adapterPosition, target.adapterPosition)
                    communicator.setWorkout(workout)
                    viewAdapter.notifyDataSetChanged()
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val workout = communicator.getWorkout()
                    workout.exercises.removeAt(viewHolder.adapterPosition)
                    communicator.setWorkout(workout)
                    viewAdapter.notifyDataSetChanged()
                }

            }).attachToRecyclerView(recyclerView)
        }catch (e : NullPointerException)
        {
            Log.e("HELIX", e.toString())
        }
    }
}