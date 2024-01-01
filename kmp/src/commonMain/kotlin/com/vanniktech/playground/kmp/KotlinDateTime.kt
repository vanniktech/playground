package com.vanniktech.playground.kmp

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/** Clock. */
fun Clock.localDateTime() = now().toLocalDateTime(TimeZone.currentSystemDefault())
fun Clock.localDate() = localDateTime().date

expect fun foo(): String
