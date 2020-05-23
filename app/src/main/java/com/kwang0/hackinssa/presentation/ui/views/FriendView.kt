package com.kwang0.hackinssa.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.presentation.ui.adapters.FriendAdapter
import java.util.ArrayList

class FriendView(private var mContext: Context?) {
    var rv: RecyclerView? = null

    private var mList: MutableList<Friend?>? = null
    private var mAdapter: FriendAdapter? = null

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.friend_rv)
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.friend_rv)
    }

    fun recyclerInit() {
        mList = ArrayList<Friend?>()
        mAdapter = FriendAdapter(mContext, mList)
        rv?.setHasFixedSize(true)
        rv?.isNestedScrollingEnabled = false
        rv?.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv?.adapter = mAdapter
    }

    fun getmList(): MutableList<Friend?>? {
        return mList
    }

    fun setmList(mList: MutableList<Friend?>?) {
        this.mList = mList
    }

    fun getmAdapter(): FriendAdapter? {
        return mAdapter
    }

    fun setmAdapter(mAdapter: FriendAdapter?) {
        this.mAdapter = mAdapter
    }
}