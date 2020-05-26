package com.kwang0.hackinssa.data.repository.impl

import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

class FriendRepositoryImpl(private val friendDao: FriendDao) : FriendRepository {
    override fun getFriend(): Flowable<Friend> {
        return friendDao.getFriend()
    }

    override fun insertOrUpdateFriend(friend: Friend): Completable {
        return friendDao.insertFriend(friend)
    }

    override fun deleteAllFriends() {
        friendDao.deleteAllFriends()
    }
}
