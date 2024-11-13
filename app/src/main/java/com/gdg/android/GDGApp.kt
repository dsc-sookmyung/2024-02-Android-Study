package com.gdg.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GDGApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}