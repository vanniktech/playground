package com.vanniktech.playground.kotlin

import okhttp3.OkHttpClient
import okhttp3.Request.Builder
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.net.ProtocolException

fun main() {
  WebSocketAbstraction(OkHttpClient.Builder().build())
}

class WebSocketAbstraction(
  private val okHttpClient: OkHttpClient,
) : WebSocketListener() {
  private fun create() = okHttpClient.newBuilder()
    .build()
    .newWebSocket(Builder().url("https://api-dev.becoach.app/websocket/coach/chat?163b198b-08ab-487e-b5b1-0648289ac0f6").build(), this)

  init {
    create()
  }

  override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
    if (t is ProtocolException && t.message == "Expected HTTP 101 response but was '401 '") {
      create()
    } else {
      t.printStackTrace()
    }
  }
}
