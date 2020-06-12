package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

interface FriendRepository {
    fun getFriends(): Flowable<List<Friend>>
    fun getFriend(friendId: String): Flowable<Friend>
    fun getFriendsFromName(friendName: String): Flowable<List<Friend>>
    fun getFriendsFromPhone(friendPhone: String): Flowable<List<Friend>>
    fun getFriendsFromEmail(friendEmail: String): Flowable<List<Friend>>
    fun getFriendsFromTagName(tagName: String): Flowable<List<Friend>>
    fun deleteFriend(friendId: String): Completable
    fun insertFriend(friend: Friend): Completable
    fun updateFriend(friend: Friend): Completable
}