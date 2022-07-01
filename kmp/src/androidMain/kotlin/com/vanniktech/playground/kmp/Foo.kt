package com.vanniktech.playground.kmp

import android.app.Activity
import android.content.Intent

inline fun <reified T : Activity> Activity.open(
  extras: (Intent) -> Intent = { it }
) {
    val intent = extras(
      Intent(this, T::class.java)
    )

    startActivity(intent)
  }
}
