package com.example.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(AppModule))
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}