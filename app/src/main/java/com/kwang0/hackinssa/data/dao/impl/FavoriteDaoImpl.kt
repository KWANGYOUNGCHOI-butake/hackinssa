package com.kwang0.hackinssa.data.dao.impl

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.models.Favorite
import io.reactivex.Completable
import io.reactivex.Flowable

class FavoriteDaoImpl(private val context: Context): FavoriteDao {
    var database: InssaDatabase
    var favoriteDao: FavoriteDao

    init {
        database = InssaDatabase.getInstance(context)
        favoriteDao = database.favoriteDao()
    }

    override fun getFavorite(favoriteName: String): Flowable<Favorite?> {
        return favoriteDao.getFavorite(favoriteName)
    }

    override fun insertFavorite(favorite: Favorite): Completable {
        return favoriteDao.insertFavorite(favorite)
    }

    override fun deleteFavorite(favorite: Favorite): Completable {
        return favoriteDao.deleteFavorite(favorite)
    }
}