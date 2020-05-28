package com.kwang0.hackinssa.data.dao

import androidx.room.*
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TagDao {

    @Query("SELECT * FROM tags")
    fun getTag(): Flowable<List<Tag>>

    @Query("SELECT * FROM tags WHERE friendId = :friendId")
    fun getTagById(friendId: String): Flowable<List<Tag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag): Completable

    @Query("DELETE FROM tags WHERE friendId = :friendId")
    fun deleteTagById(friendId: String): Completable

    @Query("DELETE FROM tags WHERE tagName = :tagName")
    fun deleteTagByName(tagName: String): Completable

    @Query("DELETE FROM tags WHERE friendId = :friendId AND tagName = :tagName")
    fun deleteTag(friendId: String, tagName: String): Completable
}