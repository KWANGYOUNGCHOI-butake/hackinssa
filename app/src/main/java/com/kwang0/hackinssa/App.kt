package com.kwang0.hackinssa

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.kwang0.hackinssa.utils.LocaleHelper


class App : Application() {
    val TAG = App::class.simpleName

    companion object {
        var localeHelper: LocaleHelper? = null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        localeHelper = LocaleHelper(base)
        super.attachBaseContext(localeHelper?.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeHelper?.setLocale(this)
    }
}