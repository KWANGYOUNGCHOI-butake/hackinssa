package com.kwang0.hackinssa.presentation.ui.activities.countryselect

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.rxbinding4.widget.textChanges
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.presentation.presenters.CountryPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.CountryPresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.helper.hideKeyboard
import com.kwang0.hackinssa.presentation.ui.views.CountryView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class CountrySelectActivity : BaseActivity() {
    val TAG = CountrySelectActivity::class.simpleName

    lateinit var search_et: EditText

    private var countryView: CountryView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_select)

        search_et = findViewById<EditText>(R.id.searchbar_et)

        countryViewSetUp()

        search_et.textChanges()
//                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
//                .filter({ chars ->
//                    val empty = TextUtils.isEmpty(chars.trim())
//                    !empty
//                })
                .subscribe({ chars ->
                    val searchTerm: String = chars.trim().toString()
                    if (chars.trim().length == 0) {
                        hideKeyboard()
                        countryView?.countryPresenter?.clear()
                    } else {
                        countryView?.countryPresenter?.search(searchTerm)
                    }
                })
    }


    fun countryViewSetUp() {
        countryView = CountryView(this)
        countryView?.bindView(this)
        countryView?.recyclerInit()
    }
}
