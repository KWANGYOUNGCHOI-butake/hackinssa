package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

interface FriendRepository {
    fun getFriends(): Flowable<List<Friend>>
    fun getFriend(friendId: String): Flowable<Friend>
    fun insertFriend(friend: Friend): Completable
    fun updateFriend(friend: Friend): Completable
}