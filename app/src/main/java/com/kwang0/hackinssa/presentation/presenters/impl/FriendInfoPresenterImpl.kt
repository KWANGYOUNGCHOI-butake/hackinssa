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
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FriendInfoPresenterImpl(context: Context, private var view: FriendInfoPresenterView): FriendInfoPresenter {
    private val TAG = FriendInfoPresenterImpl::class.simpleName

    private var friendRepository: FriendRepository
    private var tagRepository: TagRepository
    private var mDisposable = CompositeDisposable()

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(context))
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }

    override fun onStart(friendId: String) {
        mDisposable.add(friendRepository.getFriend(friendId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friend -> view.addFriendResult(friend) },
                        { throwable -> view.handleError(throwable) }) )
        mDisposable.add(tagRepository.getTagById(friendId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tags ->
                    view.addTagResultsToList(tags.toMutableList()) },
                        { throwable -> view.handleError(throwable) }))
    }

    override fun onStop() {
        view.clearTags()
        mDisposable.clear()
    }
}