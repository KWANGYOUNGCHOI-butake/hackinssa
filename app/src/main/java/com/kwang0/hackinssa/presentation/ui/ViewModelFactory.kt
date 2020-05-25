package com.kwang0.hackinssa.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kwang0.hackinssa.FriendDataSource


class ViewModelFactory(dataSource: FriendDataSource) : ViewModelProvider.Factory {
    private val mDataSource: FriendDataSource
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)) {
            return FriendViewModel(mDataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    init {
        mDataSource = dataSource
    }
}