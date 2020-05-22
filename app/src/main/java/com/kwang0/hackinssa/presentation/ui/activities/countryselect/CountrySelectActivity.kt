package com.kwang0.hackinssa.presentation.ui.activities.countryselect

import android.annotation.SuppressLint
import android.os.Bundle
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity


class CountrySelectActivity : BaseActivity() {
    val TAG = CountrySelectActivity::class.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_select)

    }
}
