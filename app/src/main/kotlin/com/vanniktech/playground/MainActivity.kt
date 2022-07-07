package com.vanniktech.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vanniktech.playground.databinding.ActivityMainBinding
import com.vanniktech.playground.kmp.Foo
import com.vanniktech.playground.kmp.open

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.root.setOnClickListener {
      open<MainActivity> {
        // Reproducer for https://youtrack.jetbrains.com/issue/KTIJ-22049
        it.putExtra("TEST", Foo.BAR)
      }
    }
  }
}
