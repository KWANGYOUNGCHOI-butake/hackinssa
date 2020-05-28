package com.kwang0.hackinssa.presentation.presenters

interface FriendAddPresenterView {
    fun finishActivity()
    fun addBtnEnabled()
    fun handleError(throwable: Throwable?)
}