package com.kwang0.hackinssa.helper

import android.util.Log
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil

object ValidHelper {
    private val TAG = ValidHelper::class.simpleName

    fun isPhoneValid(phone: String, region: String?): Boolean {
        val phoneutil = PhoneNumberUtil.getInstance()
        region?.let {
            try {
                val phoneNumber = phoneutil.parse(phone, it)
                return phoneutil.isValidNumber(phoneNumber)
            } catch (e: NumberParseException) {
                Log.e(TAG, "Exception : " + e.message)
                return false
            }
        }
        return false
    }

    fun isEmailValid(email: String): Boolean {
        return PatternHelper.EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun isTagValid(tag: String): Boolean {
        return PatternHelper.TAG_PATTERN.matcher(tag).matches()
    }
}