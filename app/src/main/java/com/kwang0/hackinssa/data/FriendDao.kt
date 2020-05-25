package com.kwang0.hackinssa.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface FriendDao {
    @Query("SELECT * FROM Friends LIMIT 1")
    fun getFriend(): Flowable<Friend>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriend(friend: Friend): Completable

    @Query("DELETE FROM Friends")
    fun deleteAllFriends()
}