package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag


interface FriendInfoPresenterView {
    fun addFriendResult(friend: Friend)
    fun addTagResultsToList(tagList: MutableList<Tag>)
    fun clearTags()
    fun handleError(throwable: Throwable?)
}