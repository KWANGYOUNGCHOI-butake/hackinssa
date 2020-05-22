package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Country: Serializable {

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("alpha2Code")
    @Expose
    private var alpha2Code: String? = null

    @SerializedName("alpha3Code")
    @Expose
    private var alpha3Code: String? = null

    @SerializedName("callingCodes")
    @Expose
    private var callingCodes: List<String?>? = null

    @SerializedName("capital")
    @Expose
    private var capital: String? = null

    @SerializedName("region")
    @Expose
    private var region: String? = null

    @SerializedName("population")
    @Expose
    private var population: String? = null

    @SerializedName("demonym")
    @Expose
    private var demonym: String? = null

    @SerializedName("timezones")
    @Expose
    private var timezones: List<String?>? = null

    @SerializedName("nativeName")
    @Expose
    private var nativeName: String? = null

    @SerializedName("currencies")
    @Expose
    private var currencies: List<Currency?>? = null

    @SerializedName("languages")
    @Expose
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