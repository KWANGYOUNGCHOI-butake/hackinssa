package com.kwang0.hackinssa.presentation.ui.activities.countryselect

import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.jakewharton.rxbinding4.widget.textChanges
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
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

        searchTextChanges(search_et)
    }

    override fun onStart() {
        super.onStart()
        countryView?.countryPresenter?.restoreData()
    }

    override fun onPause() {
        super.onPause()
        countryView?.countryPresenter?.tearDown()
    }

    // 입력 값 변화를 observing 해줌 (debounce 로 200ms 동안 기다렸다가 API를 호출)
    private fun searchTextChanges(et: EditText) {
        et.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { chars ->
                    val searchTerm: String = chars.trim().toString()
                    if (chars.trim().isEmpty()) {
                        hideKeyboard()
                        countryView?.countryPresenter?.clear()
                    } else {
                        countryView?.countryPresenter?.search(searchTerm)
                    }
                }
    }

    private fun countryViewSetUp() {
        countryView = CountryView(this)
        countryView?.bindView(this)
    }
}
