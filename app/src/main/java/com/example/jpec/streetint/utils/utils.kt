package com.example.jpec.streetint.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.net.URL

fun String.saveTo(path: String) {
    val input = URL(this).openStream()
    val output = FileOutputStream(File(path))
    input.use {
        output.use {
            input.copyTo(output)
        }
    }
}