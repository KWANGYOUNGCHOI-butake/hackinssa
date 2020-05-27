package com.kwang0.hackinssa.data.dao

import androidx.room.*
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TagDao {

    @Query("SELECT * FROM tags LIMIT 1")
    fun getTag(): Flowable<Tag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag): Completable

    @Update
    fun update(tag: Tag): Completable

    @Query("DELETE FROM tags WHERE friendId = :friendId")
    fun deleteTagById(friendId: Int): Completable

    @Query("DELETE FROM tags WHERE tagName = :tagName")
    fun deleteTagByName(tagName: String): Completable

    @Query("DELETE FROM tags WHERE friendId = :friendId AND tagName = :tagName")
    fun deleteTag(friendId: Int, tagName: String): Completable
}