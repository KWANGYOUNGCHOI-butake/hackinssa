package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

interface FriendRepository {
    fun getFriend(): Flowable<Friend>
    fun insertOrUpdateFriend(friend: Friend): Completable
}