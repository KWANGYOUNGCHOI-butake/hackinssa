package com.kwang0.hackinssa

import org.junit.Assert
import org.junit.Test
import java.util.*

class LocaleUnitTest {


    @Test
    fun locale_isValid() {
        Assert.assertEquals(true, Locale(null))
    }
}