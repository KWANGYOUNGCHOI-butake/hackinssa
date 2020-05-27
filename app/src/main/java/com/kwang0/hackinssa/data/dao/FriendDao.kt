package com.kwang0.hackinssa.data.dao

import androidx.room.*
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface FriendDao {
    @Query("SELECT * FROM friends LIMIT 1")
    fun getFriend(): Flowable<Friend>

    @Update
    fun update(friend: Friend): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriend(friend: Friend): Completable
}