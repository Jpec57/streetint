package com.example.jpec.streetint.fragments.main_activity.end_of_workout

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_end_of_workout_resume.*

class EndOfWorkoutResumeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_end_of_workout_resume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewContent()
        onClickButtons()
    }

    private fun setViewContent()
    {
        points.text = "404"
    }

    private fun onClickButtons()
    {
        end.setOnClickListener {
            startActivity(Intent(activity!!.applicationContext, MainActivity::class.java))
        }
    }
}