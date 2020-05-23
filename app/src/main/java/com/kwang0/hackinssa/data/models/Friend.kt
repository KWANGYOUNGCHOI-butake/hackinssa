package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Friend: Serializable {

    @SerializedName("avatar")
    var avatar: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("email")
    var email: String? = null

    constructor(avatar: String?, name: String?, phone: String?, email: String?) {
        this.avatar = avatar
        this.name = name
        this.phone = phone
        this.email = email
    }

}