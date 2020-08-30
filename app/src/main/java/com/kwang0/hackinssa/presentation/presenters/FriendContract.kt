package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Friend

interface FriendContract {
    interface Presenter {
        fun search()
        fun search(query: String)
        fun searchByTag(tagName: String)
        fun deleteFriend(friendId: String)
        fun tearDown()
        fun restoreData()
    }

    interface View {
        fun addResultsToList(friends: MutableList<Friend>)
        fun finishDelete()
        fun handleError(throwable: Throwable?)
    }
}