package com.kwang0.hackinssa.data.models

class Friend {

    var avatar: String? = null
    var name: String? = null
    var phone: String? = null
    var email: String? = null

    constructor(avatar: String?, name: String?, phone: String?, email: String?) {
        this.avatar = avatar
        this.name = name
        this.phone = phone
        this.email = email
    }

}