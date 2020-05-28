package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable

interface FriendAddPresenter {
    fun insertOrUpdateFriend(friendId: String?,
                             friendAvatar: String,
                             friendName: String,
                             friendPhone: String?,
                             friendEmail: String?,
                             friendCountry: String,
                             friendCreated: Long,
                             tagList: MutableList<Tag>)
}