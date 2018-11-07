package com.example.jpec.streetint.fragments.main_activity.in_workout

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.InWorkoutActivity
import com.example.jpec.streetint.activities.MainActivity
import com.example.jpec.streetint.models.Exercise
import kotlinx.android.synthetic.main.fragment_in_workout.*
import kotlin.math.min

class WorkoutExerciseViewFragment : Fragment() {
    private lateinit var ref : InWorkoutActivity
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var bip : MediaPlayer
    private lateinit var exo: Exercise
    private var first: Boolean = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_in_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bip = MediaPlayer.create(activity, R.raw.bip)
        setOnClickButtonsExercise()
        ref = this.activity as InWorkoutActivity
        setOnExerciseView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isAdded) {
            setOnExerciseView()
        }
    }



    private fun setCorrectSerie()
    {

        ref.currentSerie += 1
        if (ref.currentSerie > ref.workout!!.exercises[ref.currentExo].series)
        {
            ref.currentSerie = 1
            ref.currentExo += 1
        }
        if (ref.currentExo > ref.workout!!.exercises.size - 1)
        {
            Toast.makeText(activity, "End of your workout", Toast.LENGTH_LONG).show()
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    private fun setOnExerciseView()
    {
        exo = ref.workout!!.exercises[ref.currentExo]
        if (exo.superset != null && !first)
        {
            exo = exo.superset as Exercise
        }
        exo_name.text = exo.name
        var holdTime = 0
        exo_reps.text = if (!exo.isStatic) "${exo.reps} @ +${exo.weight}kgs" else "$holdTime"

        val delay: Long = 1000
        runnable = object : Runnable {
            override fun run() {
                val minValue = ref.time / 60
                val min = if (minValue < 10) "0$minValue" else "$minValue"
                val secValue = (ref.time - 60 * (ref.time / 60)) % 60
                val sec = if (secValue < 10) "0$secValue" else "$secValue"
                val t = "$min : $sec"
                time.text = t
                holdTime += 1
                if (exo.isStatic)
                {
                    exo_reps.text = "$holdTime"
                    if (exo.reps == holdTime)
                    {
                        bip.start()
                    }
                }
                handler.postDelayed(this, delay)
            }
        }
        handler = Handler()
        handler.postDelayed(runnable, delay)
    }




    private fun setOnClickButtonsExercise()
    {
        done.setOnClickListener {
            if (exo.superset != null && first)
            {
                first = false
                setOnExerciseView()
            }
            else
            {
                setCorrectSerie()
                handler.removeCallbacks(runnable)
                ref.mPager.currentItem = 1
            }
        }
    }
}