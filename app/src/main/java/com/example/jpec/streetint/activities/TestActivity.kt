package com.example.jpec.streetint.activities


import android.Manifest
import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import com.example.jpec.streetint.R
import kotlinx.android.synthetic.main.activity_test.*
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import java.util.*
import android.content.Intent
import android.content.CursorLoader
import android.content.pm.PackageManager
import android.media.MediaMetadataRetriever
import android.net.Uri
import java.io.IOException
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat


class TestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}
//                try {
//                    visualizer = Visualizer(song.audioSessionId)
//                    visualizer.enabled = false
//                    visualizer.captureSize = Visualizer.getCaptureSizeRange()[1]
//                    Log.e("HELIX", "${visualizer.captureSize}")
//                    visualizer.setDataCaptureListener(
//                        object : Visualizer.OnDataCaptureListener {
//                            override fun onWaveFormDataCapture(
//                                visualizer: Visualizer,
//                                bytes: ByteArray, samplingRate: Int
//                            ) {
//                            }
//
//                            override fun onFftDataCapture(
//                                visualizer: Visualizer,
//                                bytes: ByteArray, samplingRate: Int
//                            ) {
//                                //bytearray of size 1024 = capturesize
//                                // sampling rate 44100000
//                                var string = ""
//                                var i = 0
//                                var max : Short = 0
//                                var maxIndex = 0
//                                var frequency = 0
//                                for (bit in bytes)
//                                {
//                                    if (max < bit.toShort())
//                                    {
//                                        maxIndex = i
//                                        max = bit.toShort()
//                                        frequency = maxIndex *
//                                    }
//                                    i++
//                                    string += "${bit.toString()}|"
//                                    // i *
//                                }
//                                Log.e("HELIX", "${maxIndex} STRING $string")
//                            }
//                        }, Visualizer.getMaxCaptureRate() / 2, false, true
//                    )
//                    visualizer.enabled = true
//
//                }catch (e : Exception)
//                {
//                    Log.e("HELIX", e.message)
//                }