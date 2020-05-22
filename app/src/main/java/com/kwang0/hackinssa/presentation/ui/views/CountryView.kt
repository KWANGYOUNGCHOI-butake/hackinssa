package com.kwang0.hackinssa.presentation.ui.views;

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import java.util.*

class CountryView(private var mContext: Context?) {
    private var rv: RecyclerView? = null

    private var mList: List<Country>? = null
    private var mAdapter: CountryAdapter? = null

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.country_rv)
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.country_rv)
    }

    fun recyclerInit() {
        mList = ArrayList<Country>()
        mAdapter = CountryAdapter(mContext, mList)
        rv!!.setHasFixedSize(true)
        rv!!.isNestedScrollingEnabled = false
        rv!!.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv!!.adapter = mAdapter
    }

    fun getmList(): List<Country>? {
        return mList
    }

    fun setmList(mList: List<Country>?) {
        this.mList = mList
    }

    fun getmAdapter(): CountryAdapter? {
        return mAdapter
    }

    fun setmAdapter(mAdapter: CountryAdapter?) {
        this.mAdapter = mAdapter
    }


}
