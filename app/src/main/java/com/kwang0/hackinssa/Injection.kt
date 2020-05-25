package com.kwang0.hackinssa

import android.content.Context
import com.kwang0.hackinssa.data.FriendsDatabase
import com.kwang0.hackinssa.data.LocalFriendDataSource
import com.kwang0.hackinssa.presentation.ui.ViewModelFactory

class Injection {

    private fun provideUserDataSource(context: Context): FriendDataSource {
        val database: FriendsDatabase? = FriendsDatabase.getInstance(context)
        return LocalFriendDataSource(database!!.friendDao())
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory? {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}