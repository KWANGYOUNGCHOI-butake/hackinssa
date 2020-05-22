package com.kwang0.hackinssa;

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import java.util.*

class CountryView(private var mContext: Context?) {
    private var rv: RecyclerView? = null

    private var mList: List<CountryModel>? = null
    private var mAdapter: CountryAdapter? = null

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.country_rv)
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.country_rv)
    }

    fun recyclerInit() {
        mList = ArrayList<CountryModel>()
        mAdapter = CountryAdapter(mContext, mList)
        rv!!.setHasFixedSize(true)
        rv!!.isNestedScrollingEnabled = false
        rv!!.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv!!.adapter = mAdapter
    }

    fun getmList(): List<CountryModel>? {
        return mList
    }

    fun setmList(mList: List<CountryModel>?) {
        this.mList = mList
    }

    fun getmAdapter(): CountryAdapter? {
        return mAdapter
    }

    fun setmAdapter(mAdapter: CountryAdapter?) {
        this.mAdapter = mAdapter
    }


}
