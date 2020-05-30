package com.kwang0.hackinssa

import android.util.Log
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.kwang0.hackinssa.helper.ValidHelper
import org.junit.Assert
import org.junit.Test

class PhoneUnitTest {


    fun isPhoneValid(phone: String, region: String?): Boolean {
        val phoneutil = PhoneNumberUtil.getInstance()
        if(region != null) {
            try {
                val phoneNumber = phoneutil.parse(phone, region)
                return phoneutil.isValidNumber(phoneNumber)
            } catch (e: NumberParseException) {
                return false
            }
        }
        return false
    }

    @Test
    fun phone_number_korea_isValid() {
        Assert.assertEquals(true, isPhoneValid("01012345678", "KR"))
    }
    @Test
    fun phone_number_korea_isValid1() {
        Assert.assertEquals(true, isPhoneValid("07012345678", "KR"))
    }
    @Test
    fun phone_number_korea_isValid2() {
        Assert.assertEquals(true, isPhoneValid("08012345678", "KR"))
    }
    @Test
    fun phone_number_korea_isValid_lowercase() {
        Assert.assertEquals(true, isPhoneValid("08012345678", "kr"))
    }

    @Test
    fun phone_number_japan_isValid() {
        Assert.assertEquals(true, isPhoneValid("01012345678", "JP"))
    }
    @Test
    fun phone_number_japan_isValid1() {
        Assert.assertEquals(true, isPhoneValid("07012345678", "JP"))
    }
    @Test
    fun phone_number_japan_isValid2() {
        Assert.assertEquals(true, isPhoneValid("08012345678", "JP"))
    }
}