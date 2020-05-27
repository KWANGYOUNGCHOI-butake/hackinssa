package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Favorite
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteRepository {
    fun getFavorite(favoriteName: String): Flowable<Favorite?>
    fun insertFavorite(favorite: Favorite): Completable
    fun updateFavorite(favorite: Favorite): Completable
}