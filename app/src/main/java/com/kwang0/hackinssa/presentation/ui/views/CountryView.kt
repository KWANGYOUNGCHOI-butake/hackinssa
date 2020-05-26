package com.kwang0.hackinssa.presentation.ui.views;

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.presentation.presenters.CountryPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.CountryPresenterImpl
import java.util.*

class CountryView(private var mContext: Context?): CountryPresenterView {
    var TAG = CountryView::class.simpleName

    var rv: RecyclerView? = null
    var empty_tv: TextView? = null

    var countryPresenter: CountryPresenter? = null
    private var mList: MutableList<Country?>? = null
    private var mAdapter: CountryAdapter? = null

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.country_rv)
        empty_tv = a.findViewById<TextView>(R.id.reuse_empty_tv)
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.country_rv)
        empty_tv = v.findViewById<TextView>(R.id.reuse_empty_tv)
    }

    fun recyclerInit() {
        countryPresenter = CountryPresenterImpl(this)

        mList = ArrayList<Country?>()
        mAdapter = CountryAdapter(mContext, mList)
        rv?.setHasFixedSize(true)
        rv?.isNestedScrollingEnabled = false
        rv?.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv?.adapter = mAdapter

        showEmptyLayout()
    }

    override fun adapterNotifyChanges() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun addResultsToList(countries: MutableList<Country?>?) {
        Log.d(TAG, "countries : " + countries?.size)
        mAdapter?.addManyToList(countries)
        showExistLayout()
    }

    override fun handleEmpty() {
        mAdapter?.clearList()
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
        empty_tv?.visibility = View.VISIBLE
        rv?.visibility = View.GONE
    }

    fun showExistLayout() {
        empty_tv?.visibility = View.GONE
        rv?.visibility = View.VISIBLE
    }

    fun getmList(): MutableList<Country?>? {
        return mList
    }

    fun setmList(mList: MutableList<Country?>?) {
        this.mList = mList
    }

    fun getmAdapter(): CountryAdapter? {
        return mAdapter
    }

    fun setmAdapter(mAdapter: CountryAdapter?) {
        this.mAdapter = mAdapter
    }


}
