package com.kwang0.hackinssa.helper

import android.util.Log
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.kwang0.hackinssa.helper.exception.EmailException
import com.kwang0.hackinssa.helper.exception.ExceptionMessage
import com.kwang0.hackinssa.helper.exception.PhoneException

object ValidHelper {
    private val TAG = ValidHelper::class.simpleName

    fun isPhoneValid(phone: String, region: String?): Boolean {
        val phoneutil = PhoneNumberUtil.getInstance()
        if(region != null) {
            try {
                val phoneNumber = phoneutil.parse(phone, region)
                if(phoneutil.isValidNumber(phoneNumber)) return true
            } catch (e: NumberParseException) {
                Log.e(TAG, "Exception : " + e.message)
            }
        }
        throw PhoneException(ExceptionMessage.PHONE_VALID_EXCEPTION)
    }

    fun isEmailValid(email: String): Boolean {
        if(PatternHelper.EMAIL_ADDRESS_PATTERN.matcher(email).matches()) return true
        throw EmailException(ExceptionMessage.EMAIL_VALID_EXCEPTION)
    }

    fun isTagValid(tag: String): Boolean {
        return PatternHelper.TAG_PATTERN.matcher(tag).matches()
    }
}