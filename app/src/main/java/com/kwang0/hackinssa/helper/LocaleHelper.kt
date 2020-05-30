package com.kwang0.hackinssa.helper

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.preference.PreferenceManager
import java.util.*


class LocaleHelper(context: Context?) {
    private val prefs: SharedPreferences

    fun setLocale(c: Context?): Context? {
        return updateResources(c, getLanguage())
    }

    fun setNewLocale(c: Context?, language: String): Context? {
        persistLanguage(language)
        return updateResources(c, language)
    }

    fun getLanguage(): String? {
        val defaultLang: String
        if(Locale.getDefault().language.equals(LANGUAGE_KOREAN)) defaultLang = LANGUAGE_KOREAN
        else defaultLang = LANGUAGE_ENGLISH
        return prefs.getString(LANGUAGE_KEY, defaultLang)
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(language: String) {
        prefs.edit().putString(LANGUAGE_KEY, language).commit()
    }

    private fun updateResources(context: Context?, language: String?): Context? {
        var tempContext: Context? = context
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res: Resources? = tempContext?.getResources()
        val config = Configuration(res?.getConfiguration())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setSystemLocale(config, locale)
        } else {
            setSystemLocaleLegacy(config, locale)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(locale)
            tempContext = tempContext?.createConfigurationContext(config)
        } else {
            tempContext?.resources?.updateConfiguration(config, tempContext.resources.displayMetrics)
        }
        return tempContext
    }

    @SuppressWarnings("deprecation")
    fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
        config.locale = locale
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun setSystemLocale(config: Configuration, locale: Locale) {
        config.setLocale(locale)
    }

    companion object {
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_KOREAN = "ko"
        private const val LANGUAGE_KEY = "language_key"

    }

    init {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

}