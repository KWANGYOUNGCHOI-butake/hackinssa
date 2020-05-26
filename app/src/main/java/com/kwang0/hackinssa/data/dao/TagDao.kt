package com.kwang0.hackinssa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TagDao {

    @Query("SELECT * FROM tags LIMIT 1")
    fun getTag(): Flowable<Tag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag): Completable

    @Query("DELETE FROM tags")
    fun deleteTag(tag: Tag)
}