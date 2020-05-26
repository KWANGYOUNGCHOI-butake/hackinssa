package com.kwang0.hackinssa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kwang0.hackinssa.data.models.Favorite
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites LIMIT 1")
    fun getFavorite(): Flowable<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite): Completable

    @Query("DELETE FROM favorites")
    fun deleteAllFavorites()
}