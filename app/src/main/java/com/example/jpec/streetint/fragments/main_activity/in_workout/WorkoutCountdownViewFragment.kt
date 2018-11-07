package com.example.jpec.streetint.fragments.main_activity.in_workout

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.InWorkoutActivity
import com.example.jpec.streetint.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_in_workout_rest.*

class WorkoutCountdownViewFragment : Fragment() {
    private lateinit var ref: InWorkoutActivity
    lateinit var timer : CountDownTimer
    private lateinit var bip : MediaPlayer



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_in_workout_rest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ref = this.activity as InWorkoutActivity
        bip = MediaPlayer.create(activity, R.raw.bip)
        setOnClickButtonsCountdown()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            setOnCountdownView()
        }
    }

    private fun setOnCountdownView()
    {
        timer = object: CountDownTimer((ref.workout!!.exercises[ref.currentExo].rest * 1000).toLong(), 1000){
            override fun onTick(millisUntilFinished: Long){
                countdown.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                endCountDown()
            }
        }.start()
        val info = "NEXT: ${ref.currentSerie} / ${ref.workout!!.exercises[ref.currentExo].series}"
        infoSerie.text = info
        next.text = ref.workout!!.exercises[ref.currentExo].name
    }

    fun endCountDown()
    {
        timer.cancel()

        bip.start()
        val v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
        else
            v.vibrate(1000)
        ref.mPager.currentItem = 0


//        "Save in database the real number of reps done"
    }




    private fun setOnClickButtonsCountdown()
    {
        next.setOnClickListener {
            endCountDown()
        }
        quit.setOnClickListener {
            endCountDown()
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}