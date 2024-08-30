package com.vanniktech.playground

import org.junit.Test
import kotlin.test.assertEquals

class EmptyUnitTest {
  @Test fun empty() {
    assertEquals(true, BuildConfig.DEBUG)
  }
}
