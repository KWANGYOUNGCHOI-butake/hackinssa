package com.kwang0.hackinssa.data.dao.impl

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.models.Favorite
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

class FavoriteDaoImpl(private val context: Context): FavoriteDao {
    private var database: InssaDatabase? = null
    private var favoriteDao: FavoriteDao? = null

    init {
        setUpDatabase(context)
    }

    override fun getFavorite(): Flowable<Favorite> {
        TODO("Not yet implemented")
    }

    override fun insertFavorite(favorite: Favorite): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteAllFavorites() {
        TODO("Not yet implemented")
    }

    private fun setUpDatabase(context: Context) {
        database = InssaDatabase.getInstance(context)
        favoriteDao = database?.favoriteDao()
    }
}