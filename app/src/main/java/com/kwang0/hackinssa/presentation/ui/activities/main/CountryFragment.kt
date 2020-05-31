package com.kwang0.hackinssa.presentation.ui.activities.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding4.widget.textChanges
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.presentation.presenters.CountryPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.CountryPresenterImpl
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.helper.hideKeyboard
import com.kwang0.hackinssa.presentation.ui.views.CountryView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class CountryFragment : Fragment() {
    val TAG = CountryFragment::class.java.simpleName

    lateinit var search_et: EditText

    private var countryView: CountryView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_country, container, false)

        search_et = v.findViewById<EditText>(R.id.searchbar_et)

        countryViewSetUp(v)

        searchTextChanges(search_et)

        return v
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

    private fun countryViewSetUp(v: View) {
        countryView = CountryView(v.context)
        countryView?.bindView(v)
    }

}
