package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Country

interface CountryInfoPresenterView {
    fun notifyFavoriteChange(b: Boolean)
    fun starBtnEnable()
    fun starBtnDisable()
    fun startFriendAddAct(country: Country?)
    fun setCountryFlag(code: String)
    fun setNameText(name: String)
    fun setTimeText(time: String)
}