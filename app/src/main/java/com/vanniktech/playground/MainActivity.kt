package com.vanniktech.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vanniktech.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // ActivityMainBinding can't be resolved. This is due to setting android.namespace in the Gradle Block as well as removing the package from the Manifest file.
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
