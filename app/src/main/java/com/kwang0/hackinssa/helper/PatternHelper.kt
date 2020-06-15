package com.kwang0.hackinssa.helper

import java.util.regex.Pattern

object PatternHelper {
    // 이메일을 위한 regex 패턴
    val EMAIL_ADDRESS_PATTERN = Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    )

    // 태그를 위한 regex 패턴
    val TAG_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_]+$"
    )
}