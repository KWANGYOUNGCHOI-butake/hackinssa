package com.kwang0.hackinssa.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.extensions.TagMenuListener
import com.kwang0.hackinssa.presentation.ui.adapters.TagAdapter
import java.util.ArrayList

class TagView(var mContext: Context?, var menuListener: TagMenuListener) {
    var rv: RecyclerView? = null

    private var mList: MutableList<String?>? = null
    private var mAdapter: TagAdapter? = null

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.tag_rv)
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.tag_rv)
    }

    fun recyclerInit() {
        mList = ArrayList<String?>()
        mAdapter = TagAdapter(mContext, mList, menuListener)
        rv?.setHasFixedSize(true)
        rv?.isNestedScrollingEnabled = false
        rv?.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv?.adapter = mAdapter
    }

    fun getmList(): MutableList<String?>? {
        return mList
    }

    fun setmList(mList: MutableList<String?>?) {
        this.mList = mList
    }

    fun getmAdapter(): TagAdapter? {
        return mAdapter
    }

    fun setmAdapter(mAdapter: TagAdapter?) {
        this.mAdapter = mAdapter
    }
}