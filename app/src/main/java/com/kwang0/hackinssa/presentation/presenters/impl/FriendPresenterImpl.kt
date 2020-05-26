package com.kwang0.hackinssa.presentation.presenters.impl

import android.content.Context
import com.kwang0.hackinssa.data.dao.impl.FriendDaoImpl
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.FriendPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendPresenterView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

class FriendPresenterImpl(context: Context, view: FriendPresenterView) : FriendPresenter {
    private val TAG = FriendPresenterImpl::class.simpleName

    private var view: FriendPresenterView? = view
    private var friendRepository: FriendRepository
    private var friendSubscription: Disposable? = null

    private lateinit var friend: Friend

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(context))
    }

    override fun getFriendName(): Flowable<String> {
        return friendRepository.getFriend()
                .map({ friend ->
                    this.friend = friend
                    friend.name
                })
    }

    override fun updateFriend(avatar: String, name: String, phone: String, email: String, created: Int): Completable {
        this.friend = if (this.friend == null) Friend(avatar, name, phone, email, created) else Friend(this.friend.id, avatar, name, phone, email, created)
        return friendRepository.insertOrUpdateFriend(this.friend)
    }
}