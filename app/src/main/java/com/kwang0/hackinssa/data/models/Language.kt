package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Language: Serializable {

    @SerializedName("iso639_1")
    private var iso6391: String? = null

    @SerializedName("iso639_2")
    private var iso6392: String? = null

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("nativeName")
    private var nativeName: String? = null

    fun getIso6391(): String? {
        return iso6391
    }

    fun setIso6391(iso6391: String?) {
        this.iso6391 = iso6391
    }

    fun getIso6392(): String? {
        return iso6392
    }

    fun setIso6392(iso6392: String?) {
        this.iso6392 = iso6392
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getNativeName(): String? {
        return nativeName
    }

    fun setNativeName(nativeName: String?) {
        this.nativeName = nativeName
    }

}