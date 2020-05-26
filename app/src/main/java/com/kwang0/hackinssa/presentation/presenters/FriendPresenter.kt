package com.kwang0.hackinssa.presentation.presenters

import io.reactivex.Completable
import io.reactivex.Flowable

interface FriendPresenter {
    fun getFriendName(): Flowable<String>
    fun updateFriend(avatar: String, name: String, phone: String, email: String, created: Int): Completable
}