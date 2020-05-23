package com.kwang0.hackinssa.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.presentation.ui.adapters.FriendAdapter
import com.kwang0.hackinssa.presentation.ui.adapters.TagAdapter
import java.util.ArrayList

class TagView(private var mContext: Context?) {
    var rv: RecyclerView? = null

    private var mList: MutableList<Tag?>? = null
    private var mAdapter: TagAdapter? = null

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.tag_rv)
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.tag_rv)
    }

    fun recyclerInit() {
        mList = ArrayList<Tag?>()
        mAdapter = TagAdapter(mContext, mList)
        rv?.setHasFixedSize(true)
        rv?.isNestedScrollingEnabled = false
        rv?.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv?.adapter = mAdapter
    }

    fun getmList(): MutableList<Tag?>? {
        return mList
    }

    fun setmList(mList: MutableList<Tag?>?) {
        this.mList = mList
    }

    fun getmAdapter(): TagAdapter? {
        return mAdapter
    }

    fun setmAdapter(mAdapter: TagAdapter?) {
        this.mAdapter = mAdapter
    }
}