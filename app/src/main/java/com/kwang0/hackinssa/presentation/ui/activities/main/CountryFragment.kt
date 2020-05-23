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


class CountryFragment : Fragment(), CountryPresenterView {
    val TAG = CountryFragment::class.java.simpleName

    lateinit var search_et: EditText
    lateinit var empty_tv: TextView

    private var countryView: CountryView? = null
    private var countryList: MutableList<Country?>? = null
    private var countryAdapter: CountryAdapter? = null
    private var countryPresenter: CountryPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_country, container, false)

        search_et = v.findViewById<EditText>(R.id.searchbar_et)
        empty_tv = v.findViewById<TextView>(R.id.reuse_empty_tv)

        countryViewSetUp(v)

        countryPresenter = CountryPresenterImpl(this)

        countryPresenter?.setUp()

        search_et.textChanges()
                .throttleLast(100, TimeUnit.MILLISECONDS)
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
                        countryPresenter?.clear()
                    } else {
                        countryPresenter?.search(searchTerm)
                    }
                })

        return v
    }

    fun countryViewSetUp(v: View) {
        countryView = CountryView(context)
        countryView?.bindView(v)
        countryView?.recyclerInit()

        countryList = countryView?.getmList()
        countryAdapter = countryView?.getmAdapter()

        showEmptyLayout()
    }

    override fun adapterNotifyChanges() {
        countryAdapter?.notifyDataSetChanged()
    }

    override fun addResultsToList(countries: MutableList<Country?>?) {
        Log.d(TAG, "countries : " + countries?.size)
        countryAdapter?.addManyToList(countries)
        showExistLayout()
    }

    override fun handleEmpty() {
        countryAdapter?.clearList()
        showEmptyLayout()
    }

    override fun handleError(throwable: Throwable?) {
        if (throwable?.message == "HTTP 404 Not Found") {
//            showPlaceholderNoResultsLayout()
        } else {
//            showPlaceholderLayout()
        }
    }

    fun showEmptyLayout() {
        empty_tv.visibility = VISIBLE
        countryView?.rv?.visibility = GONE
    }

    fun showExistLayout() {
        empty_tv.visibility = GONE
        countryView?.rv?.visibility = VISIBLE
    }


}
