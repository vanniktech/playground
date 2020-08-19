package com.vanniktech.playground

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class MainApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    AndroidThreeTen.init(this)
    Timber.plant(Timber.DebugTree())
  }
}
