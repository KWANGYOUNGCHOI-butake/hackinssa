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

    @Delete
    fun deleteTag(tag: Tag)
}