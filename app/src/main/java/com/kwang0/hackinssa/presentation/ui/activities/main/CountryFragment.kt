package com.kwang0.hackinssa.presentation.ui.activities.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding4.widget.textChanges
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.presentation.presenters.CountryPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.CountryPresenterImpl
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.presentation.ui.extensions.Keyboard
import com.kwang0.hackinssa.presentation.ui.views.CountryView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.security.Key
import java.util.concurrent.TimeUnit


class CountryFragment : Fragment(), CountryPresenterView {
    val TAG = CountryFragment::class.java.simpleName

    private var countryList: MutableList<Country>? = null
    private var countryAdapter: CountryAdapter? = null
    private var countryPresenter: CountryPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_country, container, false)

        val search_et = v.findViewById<EditText>(R.id.searchbar_et)
        search_et.setHint(R.string.search_hint)

        val countryView = CountryView(context)
        countryView.bindView(v)
        countryView.recyclerInit()

        countryList = countryView.getmList() as MutableList<Country>
        countryAdapter = countryView.getmAdapter()

        countryPresenter = CountryPresenterImpl(this).createPresenter(this)

        countryPresenter?.setUp()

        search_et.textChanges()
                .skip(1)
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter({ chars ->
                    val empty = TextUtils.isEmpty(chars.trim())
                    !empty
                })
                .subscribe({ chars ->
                    val searchTerm: String = chars.trim().toString()
                    if (chars.trim().length == 0) {
                        Keyboard.hideSoftKeyBoard(context!!, v)
                    } else {
                        countryPresenter?.search(searchTerm)
                    }
                })

        return v
    }

    override fun adapterNotifyChanges() {
        countryAdapter?.notifyDataSetChanged()
    }

    override fun addResultsToList(countries: MutableList<Country?>?) {
        Log.d(TAG, "countries : " + countries?.size)
        countryAdapter?.addManyToList(countries)
//        showContentLayout()
    }

    override fun handleError(throwable: Throwable?) {
        if (throwable?.message == "HTTP 404 Not Found") {
//            showPlaceholderNoResultsLayout()
        } else {
//            showPlaceholderLayout()
        }
    }



}
