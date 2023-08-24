package com.vanniktech.playground.kmp

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.onDownload
import io.ktor.client.plugins.timeout
import io.ktor.client.request.headers
import io.ktor.client.request.prepareGet
import io.ktor.http.contentLength
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class SomeTest {
  @Test fun foo() {
    val result = runBlocking {
      withContext(Dispatchers.IO) {
        HttpClient({
          install(HttpTimeout)
        }).prepareGet(
          "https://api-dev.becoach.app/coach/api/video/2faf9bc2-c89f-4b37-99b8-25c43cb73737/james-template/bc6b5407-bda1-4967-aeda-d661f397d7c8"
        ) {
          headers {
            set("User-Agent", "app.becoach.android.coach.dev:0.85.0")
            set("Authorization", "Token 93e14a87-6e45-4238-99e5-4c8b5e3028c3")
          }

          onDownload { bytesSentTotal, contentLength ->
            println(
              bytesSentTotal / contentLength.toFloat()
            )
          }

          timeout {
            requestTimeoutMillis = 10.seconds.inWholeMilliseconds
          }
        }
      }
        .execute {
          println("execute")
          it
        }
    }

    assertEquals(expected = 48931, actual = result.contentLength())
  }
}
