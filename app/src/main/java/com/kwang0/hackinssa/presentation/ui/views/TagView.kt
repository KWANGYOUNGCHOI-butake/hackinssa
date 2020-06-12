package com.kwang0.hackinssa.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_CREATED
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_NAME
import com.kwang0.hackinssa.presentation.presenters.TagPresenter
import com.kwang0.hackinssa.presentation.presenters.TagPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.TagPresenterImpl
import com.kwang0.hackinssa.presentation.ui.extensions.MenuListener
import com.kwang0.hackinssa.presentation.ui.adapters.TagAdapter
import java.util.ArrayList

class TagView(var mContext: Context, var menuListener: MenuListener?): TagPresenterView {
    private val TAG = TagView::class.simpleName

    private lateinit var rv: RecyclerView
    private lateinit var empty_tv: TextView

    var tagPresenter: TagPresenter
    private var mList: MutableList<Tag> = ArrayList<Tag>()
    private var mAdapter: TagAdapter

    init {
        tagPresenter = TagPresenterImpl(mContext, this)
        mAdapter = TagAdapter(mContext, mList, menuListener)
    }

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.tag_rv)
        empty_tv = a.findViewById<TextView>(R.id.reuse_empty_tv)
        recyclerInit()
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.tag_rv)
        empty_tv = v.findViewById<TextView>(R.id.reuse_empty_tv)
        recyclerInit()
    }

    fun recyclerInit() {
        rv.setHasFixedSize(true)
        rv.isNestedScrollingEnabled = false
        rv.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv.adapter = mAdapter
    }

    fun showEmptyLayout() {
        empty_tv.visibility = View.VISIBLE
        rv.visibility = View.GONE
    }

    fun showExistLayout() {
        empty_tv.visibility = View.GONE
        rv.visibility = View.VISIBLE
    }

    fun sortNameResults() {
        mAdapter.currentSort = FLAG_SORT_NAME
        mAdapter.addManyToList(mAdapter.mData)
    }

    fun sortCreatedResults() {
        mAdapter.currentSort = FLAG_SORT_CREATED
        mAdapter.addManyToList(mAdapter.mData)
    }

    override fun addResultsToList(tags: MutableList<Tag>) {
        mAdapter.addManyToList(tags)
        showExistLayout()
    }

    override fun finishDelete() {
        menuListener?.menuChanged()
        tagPresenter.restoreData()
    }

    override fun handleEmpty() {
        mAdapter.clearList()
        showEmptyLayout()
    }

    override fun handleError(throwable: Throwable?) {
        Log.e(TAG, "Throwable : " + throwable?.message)
    }

    fun getmList(): MutableList<Tag> {
        return mList
    }

    fun setmList(mList: MutableList<Tag>) {
        this.mList = mList
    }

    fun getmAdapter(): TagAdapter {
        return mAdapter
    }

    fun setmAdapter(mAdapter: TagAdapter) {
        this.mAdapter = mAdapter
    }

}