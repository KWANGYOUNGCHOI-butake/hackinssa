package com.kwang0.hackinssa.data.repository.impl

import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

class FriendRepositoryImpl(private val friendDao: FriendDao) : FriendRepository {
    override fun getFriends(): Flowable<List<Friend>> {
        return friendDao.getFriends()
    }

    override fun getFriend(friendId: String): Flowable<Friend> {
        return friendDao.getFriend(friendId)
    }

    override fun getFriendsFromTagName(tagName: String): Flowable<List<Friend>> {
        return friendDao.getFriendsFromTagName(tagName)
    }

    override fun insertFriend(friend: Friend): Completable {
        return friendDao.insertFriend(friend)
    }

    override fun updateFriend(friend: Friend): Completable {
        return friendDao.updateFriend(friend)
    }
}
