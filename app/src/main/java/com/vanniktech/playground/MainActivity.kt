package com.vanniktech.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.vanniktech.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // BUG - Texts are overlapping unless optimizations are turned off.
//    binding.first.root.optimizationLevel = 0

    binding.first.title.text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
    binding.first.description.text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    binding.first.imageView.setImageResource(R.drawable.portrait)

    binding.second.title.text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
    binding.second.description.text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    binding.second.imageView.setImageResource(R.drawable.landscape)

    // Simulate a Landscape image where we want to have the description underneath the title.
    val layoutParamsFirst = binding.first.topBarrier.layoutParams as ConstraintLayout.LayoutParams
    binding.first.topBarrier.referencedIds = intArrayOf(R.id.title)
    layoutParamsFirst.startToStart = binding.first.root.id
    binding.first.topBarrier.layoutParams = layoutParamsFirst

    // Simulate a Portrait image where we want to have the description underneath the imageView + title.
    val layoutParamsSecond = binding.second.topBarrier.layoutParams as ConstraintLayout.LayoutParams
    binding.second.topBarrier.referencedIds = intArrayOf(R.id.imageView, R.id.title)
    layoutParamsSecond.startToStart = binding.second.root.id
    binding.second.topBarrier.layoutParams = layoutParamsSecond
  }
}
