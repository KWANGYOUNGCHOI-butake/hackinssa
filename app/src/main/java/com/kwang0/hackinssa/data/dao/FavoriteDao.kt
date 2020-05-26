package com.kwang0.hackinssa.data.dao

import androidx.room.*
import com.kwang0.hackinssa.data.models.Favorite
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites WHERE favoriteName = :favoriteName")
    fun getFavorite(favoriteName: String): Flowable<Favorite?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite): Completable

    @Delete
    fun deleteFavorite(favorite: Favorite): Completable
}