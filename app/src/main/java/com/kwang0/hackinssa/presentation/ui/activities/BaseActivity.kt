package com.kwang0.hackinssa.presentation.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.kwang0.hackinssa.App


abstract class BaseActivity: AppCompatActivity() {

    private val TAG = BaseActivity::class.simpleName

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(App.localeHelper?.setLocale(newBase))
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }
}