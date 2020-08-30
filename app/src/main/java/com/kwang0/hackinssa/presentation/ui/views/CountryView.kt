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
import com.kwang0.hackinssa.presentation.presenters.CountryContract
import com.kwang0.hackinssa.presentation.presenters.impl.CountryPresenterImpl
import java.util.*

class CountryView(private var mContext: Context): CountryContract.View {
    var TAG = CountryView::class.simpleName

    private lateinit var rv: RecyclerView
    private lateinit var empty_tv: TextView

    var countryPresenter: CountryContract.Presenter
    private var mList: MutableList<Country> = ArrayList<Country>()
    private var mAdapter: CountryAdapter

    init {
        countryPresenter = CountryPresenterImpl(this)

        mAdapter = CountryAdapter(mContext, mList)
    }

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.country_rv)
        empty_tv = a.findViewById<TextView>(R.id.reuse_empty_tv)
        recyclerInit()
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.country_rv)
        empty_tv = v.findViewById<TextView>(R.id.reuse_empty_tv)
        recyclerInit()
    }

    fun recyclerInit() {
        rv.setHasFixedSize(true)
        rv.isNestedScrollingEnabled = false
        rv.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv.adapter = mAdapter

        showEmptyLayout()
    }

    override fun addResultsToList(countries: List<Country>) {
        mAdapter.addManyToList(countries.toMutableList())
        showExistLayout()
    }

    override fun handleEmpty() {
        mAdapter.clearList()
        showEmptyLayout()
    }

    override fun handleError(throwable: Throwable?) {
        Log.e(TAG, "Throwable : " + throwable?.message)
    }

    fun showEmptyLayout() {
        empty_tv.visibility = View.VISIBLE
        rv.visibility = View.GONE
    }

    fun showExistLayout() {
        empty_tv.visibility = View.GONE
        rv.visibility = View.VISIBLE
    }

    fun getmList(): MutableList<Country> {
        return mList
    }

    fun setmList(mList: MutableList<Country>) {
        this.mList = mList
    }

    fun getmAdapter(): CountryAdapter {
        return mAdapter
    }

    fun setmAdapter(mAdapter: CountryAdapter) {
        this.mAdapter = mAdapter
    }


}
