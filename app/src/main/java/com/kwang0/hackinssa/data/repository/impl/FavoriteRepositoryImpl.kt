package com.kwang0.hackinssa.data.repository.impl

import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.models.Favorite
import com.kwang0.hackinssa.data.repository.FavoriteRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {

    override fun getFavorite(favoriteName: String): Flowable<Favorite?> {
        return favoriteDao.getFavorite(favoriteName)
    }

    override fun insertFavorite(favorite: Favorite): Completable {
        return favoriteDao.insertFavorite(favorite)
    }

    override fun updateFavorite(favorite: Favorite): Completable {
        return favoriteDao.updateFavorite(favorite)
    }
}