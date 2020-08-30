package com.kwang0.hackinssa.presentation.presenters

interface FriendAddPresenter {
    fun onCreate()
    fun onStop()
    fun onAvatarSelect()
    fun onCountrySelect()
    fun onChipAddSelect()
    fun onFriendAddSelect()
    fun imageRequestResult(data: String?)
    fun countryRequestResult(data: String?)
    fun onChipItemRemove(text:String)
}