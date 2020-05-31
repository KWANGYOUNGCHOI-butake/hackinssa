package com.kwang0.hackinssa

import com.kwang0.hackinssa.helper.PatternHelper
import com.kwang0.hackinssa.helper.ValidHelper
import com.kwang0.hackinssa.helper.exception.EmailException
import com.kwang0.hackinssa.helper.exception.ExceptionMessage
import org.junit.Assert
import org.junit.Test

class EmailUnitTest {
    fun isEmailValid(email: String): Boolean {
        return PatternHelper.EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    @Test
    fun email_isValid() {
        Assert.assertEquals(true, isEmailValid("abseoijoi@geooe.com"))
    }
    @Test
    fun email_dash_isValid() {
        Assert.assertEquals(true, isEmailValid("abse-ww@geooe.com"))
    }
    @Test
    fun email_underscore_isValid() {
        Assert.assertEquals(true, isEmailValid("abseo_ijoi@geooe.com"))
    }
    @Test
    fun email_dot_isValid() {
        Assert.assertEquals(true, isEmailValid("abs.oijoi@geooe.com"))
    }
    @Test
    fun email_first_only_isValid() {
        Assert.assertEquals(true, ValidHelper.isEmailValid("cc"))
    }
    @Test
    fun email_first_only_isValid1() {
        Assert.assertEquals(true, isEmailValid("abs.oijo@"))
    }
    @Test
    fun email_last_only_isValid() {
        Assert.assertEquals(true, isEmailValid("@gmail.com"))
    }
    @Test
    fun email_last_only_isValid1() {
        Assert.assertEquals(true, isEmailValid("@gmail."))
    }
    @Test
    fun email_last_only_isValid2() {
        Assert.assertEquals(true, isEmailValid("@gmail"))
    }
}