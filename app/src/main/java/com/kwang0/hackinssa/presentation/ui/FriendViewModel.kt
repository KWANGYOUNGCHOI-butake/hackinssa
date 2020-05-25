package com.kwang0.hackinssa.presentation.ui

import androidx.lifecycle.ViewModel
import com.kwang0.hackinssa.FriendDataSource
import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable


class FriendViewModel(dataSource: FriendDataSource) : ViewModel() {
    private var dataSource: FriendDataSource? = null
    private var friend: Friend? = null// for every emission of the user, get the user name

    /**
     * Get the user name of the user.
     *
     * @return a [Flowable] that will emit every time the user name has been updated.
     */
    fun getFriendName(): Flowable<String>? {
        return dataSource?.getFriend() // for every emission of the user, get the user name
                ?.map({ friend ->
                    this.friend = friend
                    friend.name
                })
    }

    /**
     * Update the user name.
     *
     * @param userName the new user name
     * @return a [Completable] that completes when the user name is updated
     */
    fun updateUserName(avatar: String?, name: String?, phone: String?, email: String?, tag: List<String?>?): Completable? {
        // if there's no user, create a new user.
        // if we already have a user, then, since the user object is immutable,
        // create a new user, with the id of the previous user and the updated user name.
        this.friend = if (this.friend == null) Friend(avatar, name, phone, email, tag) else Friend(this.friend?.id, avatar, name, phone, email, tag)
        return this.dataSource?.insertOrUpdateFriend(this.friend!!)
    }

    init {
        this.dataSource = dataSource
    }
}