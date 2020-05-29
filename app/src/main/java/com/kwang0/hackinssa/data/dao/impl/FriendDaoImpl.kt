package com.kwang0.hackinssa.data.dao.impl

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

class FriendDaoImpl(private val context: Context): FriendDao {
    var database: InssaDatabase
    var friendDao: FriendDao

    init {
        database = InssaDatabase.getInstance(context)
        friendDao = database.friendDao()
    }

    override fun getFriends(): Flowable<List<Friend>> {
        return friendDao.getFriends()
    }

    override fun getFriend(friendId: String): Flowable<Friend> {
        return friendDao.getFriend(friendId)
    }

    override fun getFriendsFromName(friendName: String): Flowable<List<Friend>> {
        return friendDao.getFriendsFromName(friendName)
    }

    override fun getFriendsFromPhone(friendPhone: String): Flowable<List<Friend>> {
        return friendDao.getFriendsFromPhone(friendPhone)
    }

    override fun getFriendsFromEmail(friendEmail: String): Flowable<List<Friend>> {
        return friendDao.getFriendsFromEmail(friendEmail)
    }

    override fun getFriendsFromTagName(tagName: String): Flowable<List<Friend>> {
        return friendDao.getFriendsFromTagName(tagName)
    }

    override fun updateFriend(friend: Friend): Completable {
        return friendDao.updateFriend(friend)
    }

    override fun insertFriend(friend: Friend): Completable {
        return friendDao.insertFriend(friend)
    }

}