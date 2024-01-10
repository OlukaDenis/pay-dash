package com.dennnytech.paydash

import android.app.Application
import com.dennytech.data.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}