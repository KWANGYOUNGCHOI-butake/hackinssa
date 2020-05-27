package com.kwang0.hackinssa.presentation.presenters

import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoritePresenter {
    fun isFavorite(favoriteName: String): Flowable<Boolean>
    fun insertOrUpdateFavorite(favoriteName: String): Completable
}