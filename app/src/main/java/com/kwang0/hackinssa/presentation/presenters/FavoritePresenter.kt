package com.kwang0.hackinssa.presentation.presenters

import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoritePresenter {
    fun isFavorite(favoriteName: String): Flowable<Boolean>
    fun insertFavorite(favoriteName: String): Completable
    fun deleteFavorite(favoriteName: String): Completable
}