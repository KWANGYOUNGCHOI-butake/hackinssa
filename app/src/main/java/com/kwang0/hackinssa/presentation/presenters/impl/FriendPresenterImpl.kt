package com.kwang0.hackinssa.presentation.presenters.impl

import android.content.Context
import com.kwang0.hackinssa.data.dao.impl.FriendDaoImpl
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.FriendPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendPresenterView
import io.reactivex.disposables.Disposable

class FriendPresenterImpl(context: Context, view: FriendPresenterView) : FriendPresenter {
    private val TAG = FriendPresenterImpl::class.simpleName

    private var view: FriendPresenterView? = view
    private var friendRepository: FriendRepository? = null
    private var friendSubscription: Disposable? = null

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(context))
    }
}