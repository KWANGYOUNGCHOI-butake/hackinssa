package com.kwang0.hackinssa.presentation.presenters.impl

import android.content.Context
import com.kwang0.hackinssa.data.dao.impl.FavoriteDaoImpl
import com.kwang0.hackinssa.data.dao.impl.FriendDaoImpl
import com.kwang0.hackinssa.data.models.Favorite
import com.kwang0.hackinssa.data.repository.FavoriteRepository
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.impl.FavoriteRepositoryImpl
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.CountryPresenterView
import com.kwang0.hackinssa.presentation.presenters.FavoritePresenter
import com.kwang0.hackinssa.presentation.presenters.FavoritePresenterView
import com.kwang0.hackinssa.presentation.presenters.FriendPresenterView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

class FavoritePresenterImpl(context: Context, private var view: FavoritePresenterView): FavoritePresenter {
    private val TAG = FavoritePresenterImpl::class.simpleName

    private var favoriteRepository: FavoriteRepository
    private lateinit var favoriteSubscription: Disposable

    private var favorite: Favorite ?= null

    init {
        favoriteRepository = FavoriteRepositoryImpl(FavoriteDaoImpl(context))
    }

    override fun isFavorite(favoriteName: String): Flowable<Boolean> {
        return favoriteRepository.getFavorite(favoriteName)
                .map({ favorite ->
                    this.favorite = favorite
                    favorite.isFavorite
                })
    }

    override fun insertOrUpdateFavorite(favoriteName: String): Completable {
        if(this.favorite == null) {
            return favoriteRepository.insertFavorite(Favorite(favoriteName, true))
        } else {
            favorite?.isFavorite = if(favorite?.isFavorite == true) false else true
            return favoriteRepository.updateFavorite(favorite!!)
        }
    }
}