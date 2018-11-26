package com.example.jpec.streetint.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.CursorLoader
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Vibrator
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.util.Log
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.activity_tempo_workout.*
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*

class TempoWorkoutActivity : Activity() {
    private val READ_REQUEST_CODE = 101
    private var title = ""
    private var artist = ""
    private lateinit var song : MediaPlayer
    private lateinit var vibrator: Vibrator
    private var bpm  = -1
    private var musicSelected = false
    private lateinit var intervalVibrator : TimerTask
    private lateinit var intervalTimer : Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tempo_workout)

        setupPermissions()
        setOnClickButtons()
        song = MediaPlayer.create(this, R.raw.trivium)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == READ_REQUEST_CODE) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.e("HELIX", "Permission has been denied by user")
            } else {
                Log.e("HELIX", "Permission has been granted by user")
            }
        }
    }

    private fun formatArtistNTitle()
    {
        artist = artist.toLowerCase().substringBeforeLast(' ').replace(' ', '-')
        var i = 0
        while (title[i] == ' ')
            i++
        title = title.substring(i).toLowerCase().replace(' ', '-')
        Log.e("HELIX", artist)
        Log.e("HELIX", title)
    }

    private fun getBPM()
    {
        doAsync {
            formatArtistNTitle()
            val test = "https://songbpm.com/" +
                    artist + "-" + title +
                    "?q="+artist+"%20-%20"+title.replace("-", "%20")
            Log.e("HELIX", test)
            val website = Jsoup.connect(test).get()
            val elements = website.select("div[class=level-item has-text-centered]")
            val element = elements[0]
            bpm = element.text().substringBefore(" ").toInt()
            Log.e("HELIX ->", element.text().substringBefore(" "))
        }
    }

    private fun setVibrateOnBeat()
    {
        val bps : Float = (bpm.toFloat() / 60)
        val interval = (1000 / bps).toLong()
        Log.e("HELIX", "INTERVAL $interval")
        intervalTimer = Timer()
        intervalVibrator = object : TimerTask() {
            override fun run() {
                vibrator.vibrate(100)
            }
        }
        intervalTimer.scheduleAtFixedRate(intervalVibrator, 0, interval)
    }

    private fun setOnClickButtons()
    {
        start.setOnClickListener {
            if (!musicSelected)
                pickMusic()
            else
            {
                if (bpm != -1)
                {
                    if (!song.isPlaying)
                    {
                        song.start()
                        setVibrateOnBeat()
                    }
                    else
                    {
                        intervalTimer.cancel()
                        song.pause()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            song.release()
        }
    }

    fun pickMusic() {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Log.e("HELIX", "HERE $requestCode RESULT $resultCode")
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 10) {
                val selectedMusicUri = data.data
                if (selectedMusicUri != null) {
                    val pathFromUri = getRealPathFromURI(this, selectedMusicUri)
                    val artistNtitle = pathFromUri.substringAfterLast("/").substringBefore(".mp3")
                    artist = artistNtitle.substringBefore("-")
                    title = artistNtitle.substringAfter("-")
                    getBPM()
                    song = MediaPlayer()
                    try {
                        song.setDataSource(this, Uri.parse(pathFromUri))
                        song.prepare()
                        musicSelected = true
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }


    private fun getRealPathFromURI(context: Context, contentUri: Uri): String {
        val projection = arrayOf(MediaStore.Audio.Media.DATA)
        val loader = CursorLoader(context, contentUri, projection, null, null, null)
        val cursor = loader.loadInBackground()
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }

}