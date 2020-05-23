package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Tag: Serializable{

    @SerializedName("name")
    var name: String? = null

    constructor(name: String?) {
        this.name = name
    }
}