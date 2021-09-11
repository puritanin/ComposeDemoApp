package com.bicubictwice.composedemoapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

val Any.classTag: String
    get() = this::class.java.simpleName

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(classTag, "onCreate")
    }
}
