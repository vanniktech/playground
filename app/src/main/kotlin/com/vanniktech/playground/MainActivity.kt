package com.vanniktech.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vanniktech.playground.databinding.ActivityMainBinding
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
  private val compositeDisposable = CompositeDisposable()
  private var alreadySeen = mutableSetOf<String>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  private fun mark(position: Int) {
    if (position >= 0) {
      val item = peek(position)

      if (item != null && alreadySeen.add(item.itemId) && item.read == null) {
        compositeDisposable += Single.just(5)
          .subscribe({ }, { })
      }
    }
  }

  private fun peek(position: Int): Item? = null
}

data class Item(
  val itemId: String,
  val read: Date?,
)

operator fun CompositeDisposable.plusAssign(disposable: Disposable?) {
  if (disposable != null) {
    add(disposable)
  }
}
