package com.kwang0.hackinssa.presentation.ui

import androidx.lifecycle.ViewModel
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable


class FriendViewModel(private val repository: FriendRepository) : ViewModel() {
    private var friend: Friend? = null

    fun getFriendName(): Flowable<String>? {
        return repository.getFriend()
                .map({ friend ->
                    this.friend = friend
                    friend.name
                })
    }

    fun updateUserName(avatar: String?, name: String?, phone: String?, email: String?, tag: List<String?>?): Completable? {
        this.friend = if (this.friend == null) Friend(avatar, name, phone, email, tag) else Friend(this.friend?.id, avatar, name, phone, email, tag)
        return repository.insertOrUpdateFriend(this.friend!!)
    }
}