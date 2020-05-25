package com.kwang0.hackinssa.data

import com.kwang0.hackinssa.FriendDataSource
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

class LocalFriendDataSource(friendDao: FriendDao) : FriendDataSource {

    private val friendDao: FriendDao

    override fun getFriend(): Flowable<Friend> {
        return friendDao.getFriend()
    }

    override fun insertOrUpdateFriend(friend: Friend): Completable {
        return friendDao.insertFriend(friend)
    }

    override fun deleteAllFriends() {
        friendDao.deleteAllFriends()
    }

    init {
        this.friendDao = friendDao
    }
}
