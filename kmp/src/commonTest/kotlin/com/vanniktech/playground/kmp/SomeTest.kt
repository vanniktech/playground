package com.vanniktech.playground.kmp

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class SomeTest {
  @Test fun foo() = runBlocking {
    val response = HttpClient().get("https://lexfridman.com/feed/podcast")
    assertEquals("36d4b983b1bf2e278de73dd388cbb77c", actual = response.headers[HttpHeaders.ETag])
  }
}
