package com.kwang0.hackinssa.helper

import android.util.Log
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.exception.*

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

    // 태그이름이 유효한지 확인
    fun isTagValid(tagName: String): Boolean {
        if(PatternHelper.TAG_PATTERN.matcher(tagName).matches()) return true
        throw TagException(ExceptionMessage.TAG_VALID_EXCEPTION)
    }

    // 태그에 같은 이름이 없는지 확인
    fun isTagSameValid(tags: List<Tag>): Boolean {
        if(tags.map { tag -> tag.tagName }.groupingBy { it }.eachCount().filterValues { it > 1 }.isEmpty()) return true
        throw TagSameException(ExceptionMessage.TAG_SAME_VALID_EXCEPTION)
    }

    // 태그크기가 유효한지 확인 (5 이하)
    fun isTagSizeValid(tags: List<Tag>): Boolean {
        if(tags.size <= 5) return true
        throw TagSizeException(ExceptionMessage.TAG_SIZE_VALID_EXCEPTION)
    }

    // 태그 유효성 검사
    fun isTagsValid(tags: List<Tag>) {
        tags.forEach { tag -> isTagValid(tag.tagName) }
        isTagSameValid(tags)
        isTagSizeValid(tags)
    }
}