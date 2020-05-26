package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Favorite
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteRepository {
    fun getFavorite(): Flowable<Favorite>
    fun insertOrUpdateFavorite(favorite: Favorite): Completable
    fun deleteAllFavorites()
}