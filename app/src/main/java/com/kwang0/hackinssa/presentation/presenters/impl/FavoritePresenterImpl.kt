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

    init {
        favoriteRepository = FavoriteRepositoryImpl(FavoriteDaoImpl(context))
    }

    override fun isFavorite(favoriteName: String): Flowable<Boolean> {
        return favoriteRepository.getFavorite(favoriteName)
                .map({ favorite: Favorite? ->
                    if(favorite == null) false
                    else true
                })
    }

    override fun insertFavorite(favoriteName: String): Completable {
        return favoriteRepository.insertFavorite(Favorite(favoriteName))
    }

    override fun deleteFavorite(favoriteName: String): Completable {
        return favoriteRepository.deleteFavorite(Favorite(favoriteName))
    }
}