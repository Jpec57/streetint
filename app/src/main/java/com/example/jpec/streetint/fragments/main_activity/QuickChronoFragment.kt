package com.example.jpec.streetint.fragments.main_activity

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.fragment_quick_chrono.*
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Build
import android.os.Vibrator
import android.media.MediaPlayer







class QuickChronoFragment : androidx.fragment.app.Fragment() {
    private var oldSerieView : View? = null
    private var currentSerie: Int = 6
    private var currentRest = 60
    private lateinit var timer : CountDownTimer
    private lateinit var bip : MediaPlayer


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quick_chrono, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bip = MediaPlayer.create(activity, R.raw.bip)
        setCurrentSerie(6)
        setOnClickButtons()
    }

    private fun setCurrentSerie(currSerie : Int)
    {
        currentSerie = currSerie
        oldSerieView?.setBackgroundColor(ContextCompat.getColor(activity!!.applicationContext, R.color.blueDark))
        when (currentSerie)
        {
            0 -> oldSerieView = zero
            1 -> oldSerieView = one
            2 -> oldSerieView = two
            3 -> oldSerieView = three
            4 -> oldSerieView = four
            5 -> oldSerieView = five
            6 -> oldSerieView = six
        }
        oldSerieView?.setBackgroundColor(ContextCompat.getColor(activity!!.applicationContext, R.color.greyBlack))
    }

    private fun launchCountDown(newRest: Int)
    {
        if (currentSerie > 0)
        {
            setCurrentSerie(currentSerie - 1)
        }
        currentRest = newRest
        countdown.visibility = View.VISIBLE
        time.visibility = View.INVISIBLE

        timer = object: CountDownTimer((currentRest * 1000).toLong(), 1000){
            override fun onTick(millisUntilFinished: Long){
                countdown_time.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                endCountDown()
            }
        }.start()
    }



    @Suppress("DEPRECATION")
    private fun endCountDown()
    {
        timer.cancel()
        bip.start()
        countdown.visibility = View.INVISIBLE
        time.visibility = View.VISIBLE
        val v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v.vibrate(1000)
        }
    }

    private fun setOnClickButtons()
    {
        //Set the timer buttons
        button.setOnClickListener { launchCountDown(25) }
        button2.setOnClickListener { launchCountDown(60) }
        button3.setOnClickListener { launchCountDown(90) }
        button4.setOnClickListener { launchCountDown(120) }
        button5.setOnClickListener { launchCountDown(180) }
        button6.setOnClickListener { launchCountDown(300) }


        zero.setOnClickListener { setCurrentSerie(0) }
        one.setOnClickListener { setCurrentSerie(1)  }
        two.setOnClickListener { setCurrentSerie(2)  }
        three.setOnClickListener { setCurrentSerie(3)  }
        four.setOnClickListener { setCurrentSerie(4)  }
        five.setOnClickListener { setCurrentSerie(5)  }
        six.setOnClickListener { setCurrentSerie(6)  }

        skip.setOnClickListener { endCountDown() }
    }
}