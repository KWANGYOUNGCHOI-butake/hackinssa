package com.kwang0.hackinssa

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.presentation.ui.ViewModelFactory

class Injection {
    private fun provideFriendRepository(context: Context): FriendRepository {
        val database: InssaDatabase? = InssaDatabase.getInstance(context)
        return FriendRepositoryImpl(database!!.friendDao())
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory? {
        val repository = provideFriendRepository(context)
        return ViewModelFactory(repository)
    }
}