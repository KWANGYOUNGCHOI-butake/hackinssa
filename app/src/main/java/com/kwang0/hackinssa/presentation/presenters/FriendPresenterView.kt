package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Friend

interface FriendPresenterView {
    fun addResultsToList(friends: MutableList<Friend>)
    fun finishDelete()
    fun handleError(throwable: Throwable?)
}