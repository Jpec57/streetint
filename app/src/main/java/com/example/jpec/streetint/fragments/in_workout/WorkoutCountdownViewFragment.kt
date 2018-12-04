package com.example.jpec.streetint.fragments.in_workout

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.EndOfWorkoutActivity
import com.example.jpec.streetint.activities.InWorkoutActivity
import com.example.jpec.streetint.interfaces.DbWorkerThread
import com.example.jpec.streetint.interfaces.WorkoutDatabase
import com.example.jpec.streetint.models.Workout
import com.example.jpec.streetint.utils.OnSwipeTouchListener
import kotlinx.android.synthetic.main.fragment_in_workout_rest.*
import java.util.concurrent.TimeUnit
import kotlin.math.absoluteValue

class WorkoutCountdownViewFragment : androidx.fragment.app.Fragment() {
    private lateinit var ref: InWorkoutActivity
    lateinit var timer : CountDownTimer
    private lateinit var bip : MediaPlayer
    private lateinit var countdown_sound : MediaPlayer
    private var centralNumber: Int = 2
    private var oldExo = 0
    private var oldSerie = 1
    private var end = false

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var mDb: WorkoutDatabase? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_in_workout_rest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ref = this.activity as InWorkoutActivity
        bip = MediaPlayer.create(activity, R.raw.bip)
        countdown_sound = MediaPlayer.create(activity, R.raw.countdown)
        setOnClickButtonsCountdown()
        setDatabase()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            setOnCountdownView()
        }
    }

    private fun setCorrectSerie()
    {
        oldExo = ref.currentExo
        oldSerie = ref.currentSerie
        centralNumber = ref.workout!!.exercises[oldExo].reps
        if (ref.currentSerie >= ref.workout!!.exercises[ref.currentExo].series)
        {
            ref.currentSerie = 1
            ref.currentExo += 1
        }
        else
            ref.currentSerie += 1
        if (ref.currentExo > ref.workout!!.exercises.size - 1)
        {
            end = true
            countdown.visibility = View.INVISIBLE
            infoSerie.visibility = View.INVISIBLE
            imageView.visibility = View.INVISIBLE
            next.text = getString(R.string.end)
        }
    }

    private fun setOnCountdownView()
    {
        setCorrectSerie()
        if (!end)
        {
            timer = object: CountDownTimer((ref.workout!!.exercises[ref.currentExo].rest * 1000).toLong(), 1000){
                override fun onTick(millisUntilFinished: Long){
                    countdown.text = (millisUntilFinished / 1000).toString()
                    if (countdown.text == "3")
                    {
                        countdown_sound.start()
                    }
                }

                override fun onFinish() {
                    endCountDown()
                }
            }.start()
            val info = "NEXT: ${ref.currentSerie} / ${ref.workout!!.exercises[ref.currentExo].series}"
            infoSerie.text = info
            next.text = ref.workout!!.exercises[ref.currentExo].name
            next_desc.text = ref.workout!!.exercises[ref.currentExo].description
            imageView.setOnClickListener {
                if (next_desc.visibility == View.INVISIBLE)
                {
                    imageView.alpha = 0.6f
                    next_desc.visibility = View.VISIBLE
                }
                else
                {
                    imageView.alpha = 1.0f
                    next_desc.visibility = View.INVISIBLE
                }
            }

            setRepSliderContent(0)
            slider.setOnTouchListener(object : OnSwipeTouchListener(activity!!.applicationContext){
                override fun onSwipeLeft(velocityX: Float, distanceX: Float) {
                    setRepSliderContent(calibrateVelocity(velocityX, distanceX), true)
                }

                override fun onSwipeRight(velocityX: Float, distanceX: Float) {
                    setRepSliderContent(calibrateVelocity(velocityX, distanceX))
                }

            })
        }
    }

    private fun setCorrectDoneReps()
    {
        ref.doneWorkout!!.exercises[oldExo].reps += centralNumber
    }

    private fun endCountDown()
    {
        timer.cancel()
        setCorrectDoneReps()
        if (!end)
        {
            bip.start()
            val v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
            else
                v.vibrate(1000)
            ref.mPager.currentItem = 0
        }
        else
        {
            saveWorkoutInDb()
        }
    }

    private fun setDatabase()
    {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = WorkoutDatabase.getInstance(activity!!.applicationContext)
    }

    private fun saveWorkoutInDb()
    {
        ref.doneWorkout!!.timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        val task = Runnable {
            mDb?.workoutDao()?.insertWorkout(ref.doneWorkout as Workout)
            val intent = Intent(activity!!.applicationContext, EndOfWorkoutActivity::class.java)
            intent.putExtra("name", ref.workout!!.name)
            intent.putExtra("type", ref.workoutType)
            intent.putExtra("skillName", ref.skillName)
            startActivity(intent)
        }
        mDbWorkerThread.postTask(task)
    }

    private fun calibrateVelocity(velocityX: Float, distanceX: Float): Int
    {
        val calibratedVelocity = (velocityX / 50).absoluteValue.toInt()
        return if (calibratedVelocity > 10) 10 else calibratedVelocity
    }

    private fun setRepSliderContent(difference: Int, isLeft: Boolean = false)
    {
        centralNumber = if (!isLeft)
        {
            if (centralNumber - difference < 0) 0 else centralNumber - difference
        }
        else
            centralNumber + difference
        first.text = if (centralNumber - 2 > 0) "${centralNumber - 2}" else "${0}"
        second.text = if (centralNumber - 1 > 0) "${centralNumber - 1}" else "${0}"
        third.text = "$centralNumber"
        fourth.text = "${centralNumber + 1}"
        fifth.text = "${centralNumber + 2}"
    }


    private fun setOnClickButtonsCountdown()
    {
        next.setOnClickListener {
            endCountDown()
        }
        quit.setOnClickListener {
            end = true
            endCountDown()
        }
        first.setOnClickListener { setRepSliderContent(2) }
        second.setOnClickListener { setRepSliderContent(1) }
        fourth.setOnClickListener { setRepSliderContent(1, true) }
        fifth.setOnClickListener { setRepSliderContent(2, true) }
    }

    override fun onDestroy() {
        WorkoutDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }
}