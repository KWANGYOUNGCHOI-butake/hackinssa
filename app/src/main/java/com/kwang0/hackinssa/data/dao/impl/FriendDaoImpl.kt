package com.kwang0.hackinssa.data.dao.impl

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.remote.CountryRepositoryRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FriendDaoImpl(private val context: Context): FriendDao {
    private var database: InssaDatabase? = null
    private var friendDao: FriendDao? = null

    init {
        setUpDatabase(context)
    }

    override fun getFriend(): Flowable<Friend> {
        TODO("Not yet implemented")
    }

    override fun insertFriend(friend: Friend): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteAllFriends() {
        TODO("Not yet implemented")
    }

    private fun setUpDatabase(context: Context) {
        database = InssaDatabase.getInstance(context)
        friendDao = database?.friendDao()
    }
}