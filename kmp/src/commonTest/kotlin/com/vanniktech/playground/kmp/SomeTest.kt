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
          "https://api-dev.becoach.app/coach/api/video/c6e7ab46-a98b-4725-9d75-6f3cd54499dd/james-template/ee3b24de-ffc8-4a89-9df1-d8e99c2fb023"
        ) {
          headers {
            set("User-Agent", "app.becoach.android.coach.dev:0.85.0")
            set("Authorization", "Token 7199819a-8a5b-49bf-b0a4-c714c0d8cd77")
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
