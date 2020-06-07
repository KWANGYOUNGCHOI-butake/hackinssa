package com.kwang0.hackinssa

import android.text.TextUtils
import com.kwang0.hackinssa.helper.ValidHelper
import org.junit.Assert
import org.junit.Test

class FriendAddUnitTest {

    @Test
    fun phoneIsEmpty() {
        val friendName = "kwang0"
        val friendPhone = ""
        val friendEmail = "cky1203@gmail.com"
        if(friendName != "" &&
                ((friendPhone != "" && ValidHelper.isPhoneValid(friendPhone, "ko")) ||
                        (friendEmail != "" && ValidHelper.isEmailValid(friendEmail)))) {
            println("if")
        } else {
            println("else")
        }
    }
}