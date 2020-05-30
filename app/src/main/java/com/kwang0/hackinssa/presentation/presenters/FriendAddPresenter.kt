package com.kwang0.hackinssa.presentation.presenters

import android.content.Intent
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable

interface FriendAddPresenter {
    fun onCreate()
    fun onAvatarSelect()
    fun onCountrySelect()
    fun onChipAddSelect()
    fun onFriendAddSelect()
    fun imageRequestResult(data: String?)
    fun countryRequestResult(data: String?)
    fun onChipItemRemove(text:String)
}