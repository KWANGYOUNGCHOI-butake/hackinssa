package com.kwang0.hackinssa.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.presentation.presenters.FriendPresenter
import com.kwang0.hackinssa.presentation.presenters.TagPresenter
import com.kwang0.hackinssa.presentation.presenters.TagPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.TagPresenterImpl
import com.kwang0.hackinssa.presentation.ui.extensions.TagMenuListener
import com.kwang0.hackinssa.presentation.ui.adapters.TagAdapter
import java.util.ArrayList

class TagView(var mContext: Context, var menuListener: TagMenuListener): TagPresenterView {
    var rv: RecyclerView? = null
    var empty_tv: TextView? = null

    var tagPresenter: TagPresenter? = null
    private var mList: MutableList<Tag> = ArrayList<Tag>()
    private var mAdapter: TagAdapter? = null

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.tag_rv)
        empty_tv = a.findViewById<TextView>(R.id.reuse_empty_tv)
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.tag_rv)
        empty_tv = v.findViewById<TextView>(R.id.reuse_empty_tv)
    }

    fun recyclerInit() {
        tagPresenter = TagPresenterImpl(mContext, this)

        mAdapter = TagAdapter(mContext, mList, menuListener)
        rv?.setHasFixedSize(true)
        rv?.isNestedScrollingEnabled = false
        rv?.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv?.adapter = mAdapter
    }

    fun getmList(): MutableList<Tag> {
        return mList
    }

    fun setmList(mList: MutableList<Tag>) {
        this.mList = mList
    }

    fun getmAdapter(): TagAdapter? {
        return mAdapter
    }

    fun setmAdapter(mAdapter: TagAdapter?) {
        this.mAdapter = mAdapter
    }

    override fun addResultsToList(tags: MutableList<Tag>) {
        mAdapter?.addManyToList(tags)
    }

    override fun handleEmpty() {
        mAdapter?.clearList()
    }

    override fun handleError(throwable: Throwable?) {
    }
}