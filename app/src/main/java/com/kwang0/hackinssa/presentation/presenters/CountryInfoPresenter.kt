package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Completable
import io.reactivex.Flowable

interface CountryInfoPresenter {
    fun onCreate()
    fun onCreateStarMenu(countryName: String)
    fun onStop()
    fun onFavoriteChange(countryName: String)
    fun onFriendAddSelect(country: Country?)
}