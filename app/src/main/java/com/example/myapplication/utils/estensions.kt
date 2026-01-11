package com.example.myapplication.utils

import android.content.Context
import android.net.Uri
import android.os.SystemClock
import android.view.View
import java.io.File
import java.io.FileOutputStream

fun View.tap(interval: Long = 1000L, action: (View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < interval) return@setOnClickListener
        lastClickTime = SystemClock.elapsedRealtime()
        action(it)
    }
}

fun Uri.copyToCacheFile(context: Context, filename: String): File? {
    return try {
        val file = File(context.cacheDir, filename)
        context.contentResolver.openInputStream(this)?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}