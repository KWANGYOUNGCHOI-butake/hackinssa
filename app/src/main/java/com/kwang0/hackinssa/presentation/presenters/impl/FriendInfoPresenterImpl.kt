package com.kwang0.hackinssa.presentation.presenters.impl

import android.content.Context
import com.kwang0.hackinssa.data.dao.impl.FriendDaoImpl
import com.kwang0.hackinssa.data.dao.impl.TagDaoImpl
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.TagRepository
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.data.repository.impl.TagRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.FriendInfoPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendInfoPresenterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FriendInfoPresenterImpl(context: Context, private var view: FriendInfoPresenterView): FriendInfoPresenter {
    private val TAG = FriendInfoPresenterImpl::class.simpleName

    private var friendRepository: FriendRepository
    private var tagRepository: TagRepository
    private var friendInfoSubscription: Disposable? = null

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(context))
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }


    override fun search(friendId: String) {
        friendInfoSubscription = friendRepository.getFriend(friendId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext({tagRepository.getTagById(friendId)
                        .subscribe({ tags -> view.addTagResultsToList(tags.toMutableList()) },
                                { throwable -> view.handleError(throwable) })})
                .subscribe({ friend -> view.addFriendResult(friend) },
                        { throwable -> view.handleError(throwable) })
    }
}