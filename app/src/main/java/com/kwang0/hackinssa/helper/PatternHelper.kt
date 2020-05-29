package com.kwang0.hackinssa.helper

import java.util.regex.Pattern

object PatternHelper {
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    )
    val TAG_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_]+$"
    )
}