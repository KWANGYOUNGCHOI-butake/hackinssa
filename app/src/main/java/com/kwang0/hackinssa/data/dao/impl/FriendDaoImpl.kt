package com.kwang0.hackinssa.data.dao.impl

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

class FriendDaoImpl(private val context: Context): FriendDao {
    private var database: InssaDatabase? = null
    private var friendDao: FriendDao? = null

    init {
        database = InssaDatabase.getInstance(context)
        friendDao = database?.friendDao()
    }

    override fun getFriend(): Flowable<Friend> {
        TODO("Not yet implemented")
    }

    override fun update(friend: Friend): Completable {
        TODO("Not yet implemented")
    }

    override fun insertFriend(friend: Friend): Completable {
        TODO("Not yet implemented")
    }

}