package com.kwang0.hackinssa.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.helper.FlagHelper
import com.kwang0.hackinssa.presentation.presenters.FriendContract
import com.kwang0.hackinssa.presentation.presenters.impl.FriendPresenterImpl
import com.kwang0.hackinssa.presentation.ui.adapters.FriendAdapter
import com.kwang0.hackinssa.presentation.ui.extensions.MenuListener
import java.util.ArrayList

class FriendView(private var mContext: Context, var menuListener: MenuListener? = null): FriendContract.View {
    private val TAG = FriendView::class.simpleName

    private lateinit var rv: RecyclerView

    var friendPresenter: FriendContract.Presenter
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

    override fun finishDelete() {
        menuListener?.menuChanged()
        friendPresenter.restoreData()
    }

    override fun handleError(throwable: Throwable?) {
        Log.e(TAG, "Throwable : " + throwable?.message)
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
}