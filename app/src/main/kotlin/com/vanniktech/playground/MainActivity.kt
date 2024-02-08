package com.vanniktech.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.cash.sqldelight.Query
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.vanniktech.playground.databinding.ActivityMainBinding
import com.vanniktech.playground.kmp.QueryWrapper
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.Sink
import okio.buffer
import okio.sink
import okio.use

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val sqlDriver = AndroidSqliteDriver(
            schema = QueryWrapper.Schema,
            context = this,
            name = "Database.db",
    )

    val queryWrapper = QueryWrapper(sqlDriver)

    val query = queryWrapper.fooQueries.all()
    query.addListener(object : Query.Listener {
      override fun queryResultsChanged() {
        query.executeAsList()
      }
    })

    CoroutineScope(Dispatchers.IO).launch {
      val client = HttpClient(OkHttp)
      val url = "https://pdst.fm/e/chrt.fm/track/G454/prfx.byspotify.com/e/traffic.megaphone.fm/SIXMSB6632024451.mp3?updated=1706729405"
      val sink = applicationContext.filesDir.resolve("file.mp3").sink()
      client.prepareGet(url) {
        onDownload { bytesSentTotal, contentLength ->
          val progress = (bytesSentTotal / contentLength.toFloat() * 100f).toLong()
          queryWrapper.fooQueries.upsert(
                  Foo(
                          id = "id",
                          progress = progress,
                  )
          )
        }
      }.execute {

        it.bodyAsChannel().readFully(sink)
      }
    }
  }
}


/** https://youtrack.jetbrains.com/issue/KTOR-5066/Integration-with-okio */

// Okio likes to use 8kb:
// https://github.com/square/okio/blob/a94c678de4e8a21e53126d42a1a3d897daa56a4a/recipes/index.html#L1322
private const val OKIO_RECOMMENDED_BUFFER_SIZE: Int = 8192

@Suppress("NAME_SHADOWING")
private suspend fun ByteReadChannel.readFully(sink: Sink) {
  val channel = this
  sink.buffer().use { sink ->
    while (!channel.isClosedForRead) {
      val packet = channel.readRemaining(OKIO_RECOMMENDED_BUFFER_SIZE.toLong())
      while (!packet.isEmpty) {
        sink.write(packet.readBytes())
      }
    }
  }
}
