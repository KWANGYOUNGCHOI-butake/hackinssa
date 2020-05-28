package com.kwang0.hackinssa.data.dao

import androidx.room.*
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface FriendDao {
    @Query("SELECT * FROM friends")
    fun getFriends(): Flowable<List<Friend>>

    @Query("SELECT * FROM friends WHERE friendId = :friendId LIMIT 1")
    fun getFriend(friendId: String): Flowable<Friend>

    @Update
    fun updateFriend(friend: Friend): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriend(friend: Friend): Completable
}