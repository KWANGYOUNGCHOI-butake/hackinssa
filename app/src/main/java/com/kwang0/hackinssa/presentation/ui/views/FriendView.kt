package com.kwang0.hackinssa.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.helper.FlagHelper
import com.kwang0.hackinssa.presentation.presenters.CountryPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.CountryPresenterImpl
import com.kwang0.hackinssa.presentation.presenters.impl.FriendPresenterImpl
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.presentation.ui.adapters.FriendAdapter
import java.util.ArrayList

class FriendView(private var mContext: Context): FriendPresenterView {
    private lateinit var rv: RecyclerView

    var friendPresenter: FriendPresenter
    private var mList: MutableList<Friend> = ArrayList<Friend>()
    private var mAdapter: FriendAdapter

    init {
        friendPresenter = FriendPresenterImpl(mContext, this)

        mAdapter = FriendAdapter(mContext, mList)
    }

    fun bindView(a: Activity) {
        rv = a.findViewById<RecyclerView>(R.id.friend_rv)
        recyclerInit()
    }

    fun bindView(v: View) {
        rv = v.findViewById<RecyclerView>(R.id.friend_rv)
        recyclerInit()
    }

    fun recyclerInit() {
        rv.setHasFixedSize(true)
        rv.isNestedScrollingEnabled = false
        rv.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv.adapter = mAdapter
    }

    fun getmList(): MutableList<Friend> {
        return mList
    }

    fun setmList(mList: MutableList<Friend>) {
        this.mList = mList
    }

    fun getmAdapter(): FriendAdapter {
        return mAdapter
    }

    fun setmAdapter(mAdapter: FriendAdapter) {
        this.mAdapter = mAdapter
    }

    fun sortNameResults() {
        mAdapter.currentSort = FlagHelper.FLAG_SORT_NAME
        mAdapter.addManyToList(mAdapter.mData)
    }

    fun sortCreatedResults() {
        mAdapter.currentSort = FlagHelper.FLAG_SORT_CREATED
        mAdapter.addManyToList(mAdapter.mData)
    }

    override fun addResultsToList(friends: MutableList<Friend>) {
        mAdapter.addManyToList(friends)
    }

    override fun handleEmpty() {
        mAdapter.clearList()
    }

    override fun handleError(throwable: Throwable?) {
        Log.d("friendView", "thrwoable:")
    }
}