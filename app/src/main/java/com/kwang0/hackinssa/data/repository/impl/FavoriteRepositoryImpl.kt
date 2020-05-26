package com.kwang0.hackinssa.data.repository.impl

import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.models.Favorite
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.repository.FavoriteRepository
import com.kwang0.hackinssa.data.repository.FriendRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {

    override fun getFavorite(): Flowable<Favorite> {
        return favoriteDao.getFavorite()
    }

    override fun insertOrUpdateFavorite(favorite: Favorite): Completable {
        return favoriteDao.insertFavorite(favorite)
    }

    override fun deleteAllFavorites() {
        return favoriteDao.deleteAllFavorites()
    }
}