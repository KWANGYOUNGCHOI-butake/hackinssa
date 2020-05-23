package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Country: Serializable {

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("alpha2Code")
    private var alpha2Code: String? = null

    @SerializedName("alpha3Code")
    private var alpha3Code: String? = null

    @SerializedName("callingCodes")
    private var callingCodes: List<String?>? = null

    @SerializedName("capital")
    private var capital: String? = null

    @SerializedName("region")
    private var region: String? = null

    @SerializedName("population")
    private var population: String? = null

    @SerializedName("demonym")
    private var demonym: String? = null

    @SerializedName("timezones")
    private var timezones: List<String?>? = null

    @SerializedName("nativeName")
    private var nativeName: String? = null

    @SerializedName("currencies")
    private var currencies: List<Currency?>? = null

    @SerializedName("languages")
    private var languages: List<Language?>? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getAlpha2Code(): String? {
        return alpha2Code
    }

    fun setAlpha2Code(alpha2Code: String?) {
        this.alpha2Code = alpha2Code
    }

    fun getAlpha3Code(): String? {
        return alpha3Code
    }

    fun setAlpha3Code(alpha3Code: String?) {
        this.alpha3Code = alpha3Code
    }

    fun getCallingCodes(): List<String?>? {
        return callingCodes
    }

    fun setCallingCodes(callingCodes: List<String?>?) {
        this.callingCodes = callingCodes
    }

    fun getCapital(): String? {
        return capital
    }

    fun setCapital(capital: String?) {
        this.capital = capital
    }

    fun getRegion(): String? {
        return region
    }

    fun setRegion(region: String?) {
        this.region = region
    }

    fun getPopulation(): String? {
        return population
    }

    fun setPopulation(population: String?) {
        this.population = population
    }

    fun getDemonym(): String? {
        return demonym
    }

    fun setDemonym(demonym: String?) {
        this.demonym = demonym
    }

    fun getTimezones(): List<String?>? {
        return timezones
    }

    fun setTimezones(timezones: List<String?>?) {
        this.timezones = timezones
    }

    fun getNativeName(): String? {
        return nativeName
    }

    fun setNativeName(nativeName: String?) {
        this.nativeName = nativeName
    }

    fun getCurrencies(): List<Currency?>? {
        return currencies
    }

    fun setCurrencies(currencies: List<Currency?>?) {
        this.currencies = currencies
    }

    fun getLanguages(): List<Language?>? {
        return languages
    }

    fun setLanguages(languages: List<Language?>?) {
        this.languages = languages
    }
}