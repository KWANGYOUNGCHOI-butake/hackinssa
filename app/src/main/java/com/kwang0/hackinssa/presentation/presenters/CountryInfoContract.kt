package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Country

interface CountryInfoContract {
    interface Presenter {
        fun onCreate()
        fun onStart()
        fun onStop()
        fun onFavoriteChange()
        fun onFriendAddSelect()
    }

    interface View {
        fun notifyFavoriteChange(b: Boolean)
        fun starBtnEnable()
        fun starBtnDisable()
        fun startFriendAddAct(country: Country?)
        fun handleError(throwable: Throwable?)
        fun setCountryFlag(code: String)
        fun setNameText(name: String)
        fun setTimeText(time: String)
    }
}