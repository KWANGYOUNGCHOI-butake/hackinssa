package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Currency: Serializable {
    @SerializedName("code")
    private var code: String? = null

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("symbol")
    private var symbol: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getSymbol(): String? {
        return symbol
    }

    fun setSymbol(symbol: String?) {
        this.symbol = symbol
    }
}