package com.example.jpec.streetint.fragments.createWorkout

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jpec.streetint.R
import com.example.jpec.streetint.adapters.ChooseExoAdapter
import com.example.jpec.streetint.interfaces.CreateWorkoutCommunicator
import com.example.jpec.streetint.models.Exercise
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_choose_exo.*


class ChooseExoFragment : androidx.fragment.app.Fragment() {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private lateinit var mDbRef : DatabaseReference
    private lateinit var communicator: CreateWorkoutCommunicator

    private var exoList : ArrayList<Exercise> = ArrayList()
    private var series = 5
    private var reps = 5
    private var rest = 90


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_exo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser)
            setExoListWithFirebase()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        communicator = activity as CreateWorkoutCommunicator
    }

    private fun setDefaultPreference()
    {

    }

    private fun setExoListWithFirebase()
    {
        mDbRef = FirebaseDatabase.getInstance().reference.child("exercises")
        mDbRef.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled ${databaseError.toException()}")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    exoList = ArrayList()
                    for (item in dataSnapshot.children)
                    {
                        val musclesList = ArrayList<String>()
                        var contained = false
                        for (muscle in item.child("muscles").children)
                        {
                            musclesList.add(muscle.getValue(String::class.java)!!)
                            if (communicator.getMuscle() == muscle.getValue(String::class.java)!!)
                                contained = true
                        }
                        if (!contained)
                            continue
                        val materialList = ArrayList<String>()
                        for (material in item.child("material").children)
                        {
                            materialList.add(material.getValue(String::class.java)!!)
                        }
                        val diff = item.child("difficulty").getValue(Float::class.java) ?: 0.0f
                        val isStatic = item.child("hold").getValue(Boolean::class.java) ?: false
                        exoList.add(Exercise(name= item.key!!,
                            muscles = musclesList,
                            material = materialList,
                            difficulty = diff,
                            isStatic = isStatic,
                            series = series,
                            reps = reps,
                            rest = rest))
                    }
                    setAdapter()

                }

            })
    }

    private fun setAdapter()
    {
        viewManager = LinearLayoutManager(activity)
        val viewAdapter = ChooseExoAdapter(exoList, communicator)
        recyclerView = activity!!.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_exo).apply {
            layoutManager = viewManager
            adapter = viewAdapter
            setHasFixedSize(true)
        }
        searchBox.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                motionLayout.transitionToEnd()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewAdapter.filter.filter(s.toString())
            }
        })

    }
}