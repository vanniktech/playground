package com.vanniktech.playground.kmp

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.io.SourceReader
import com.fleeksoft.ksoup.io.from
import com.fleeksoft.ksoup.parser.Parser
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import kotlinx.coroutines.runBlocking

object Ksouper {
  suspend fun foo() {
    val client = HttpClient()
    val url = "https://modernwisdom.libsyn.com/rss"

    println("Ksoup - Calling $url")

    val sourceReader = SourceReader.from(
      client.get(url).bodyAsChannel()
    )

    println("Ksoup - sourceReader=$sourceReader")

    val document = Ksoup.parse(
      sourceReader = sourceReader,
      baseUri = url,
      charsetName = "utf-8",
      parser = Parser.xmlParser(),
    )

    println("Ksoup ${document.root().children()}")
  }

}
