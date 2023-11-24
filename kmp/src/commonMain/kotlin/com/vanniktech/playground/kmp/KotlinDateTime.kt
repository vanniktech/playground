package com.vanniktech.playground.kmp

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import app.cash.sqldelight.async.coroutines.synchronous

/** Clock. */
fun Clock.localDateTime() = now().toLocalDateTime(TimeZone.currentSystemDefault())
fun Clock.localDate() = localDateTime().date

fun foo() {
  QueryWrapper.Schema.synchronous()
}
