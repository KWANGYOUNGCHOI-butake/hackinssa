package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Country: Serializable {

    @SerializedName("name")
    private var name: String = ""

    @SerializedName("alpha2Code")
    private var alpha2Code: String = ""

    @SerializedName("callingCodes")
    private var callingCodes: List<String> = listOf()

    @SerializedName("capital")
    private var capital: String = ""

    @SerializedName("region")
    private var region: String = ""

    @SerializedName("population")
    private var population: String = ""

    @SerializedName("timezones")
    private var timezones: List<String> = listOf()

    @SerializedName("nativeName")
    private var nativeName: String = ""

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getAlpha2Code(): String {
        return alpha2Code
    }

    fun setAlpha2Code(alpha2Code: String) {
        this.alpha2Code = alpha2Code
    }

    fun getCallingCodes(): List<String> {
        return callingCodes
    }

    fun setCallingCodes(callingCodes: List<String>) {
        this.callingCodes = callingCodes
    }

    fun getCapital(): String {
        return capital
    }

    fun setCapital(capital: String) {
        this.capital = capital
    }

    fun getRegion(): String {
        return region
    }

    fun setRegion(region: String) {
        this.region = region
    }

    fun getPopulation(): String {
        return population
    }

    fun setPopulation(population: String) {
        this.population = population
    }

    fun getTimezones(): List<String> {
        return timezones
    }

    fun setTimezones(timezones: List<String>) {
        this.timezones = timezones
    }

    fun getNativeName(): String {
        return nativeName
    }

    fun setNativeName(nativeName: String) {
        this.nativeName = nativeName
    }
}