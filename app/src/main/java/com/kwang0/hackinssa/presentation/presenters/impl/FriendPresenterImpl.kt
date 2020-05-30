package com.kwang0.hackinssa.presentation.presenters.impl

import android.content.Context
import com.kwang0.hackinssa.data.dao.impl.FriendDaoImpl
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.FriendPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendPresenterView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class FriendPresenterImpl(context: Context, private var view: FriendPresenterView) : FriendPresenter {
    private val TAG = FriendPresenterImpl::class.simpleName
    private val OPERATION_ALL = 0
    private val OPERATION_QUERY = 1
    private val OPERATION_TAG = 2

    private var friendRepository: FriendRepository
    private var friendSubscription: Disposable? = null

    private var currentOperation: Int? = null
    private var query: String = ""
    private var tagName: String = ""

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(context))
    }

    override fun search() {
        this.currentOperation = OPERATION_ALL
        friendSubscription = friendRepository.getFriends()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friends ->
                    view.addResultsToList(friends.toMutableList()) }, { throwable -> view.handleError(throwable) })
    }

    override fun search(query: String) {
        this.currentOperation = OPERATION_QUERY
        this.query = query

        tearDown()

        val nameRequest = friendRepository.getFriendsFromName(query).onErrorReturn { e -> listOf<Friend>() }
        val phoneRequest = friendRepository.getFriendsFromPhone(query).onErrorReturn { e -> listOf<Friend>() }
        val emailRequest = friendRepository.getFriendsFromEmail(query).onErrorReturn { e -> listOf<Friend>() }

        friendSubscription = Flowable.zip(nameRequest, phoneRequest, emailRequest,
                Function3<List<Friend>, List<Friend>, List<Friend>, List<Friend>> {
                    name, phone, email ->
                    val allFriends = name.toMutableList().plus(phone.toMutableList()).plus(email.toMutableList())
                    return@Function3 allFriends.distinctBy { it.friendId }.toMutableList()
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friends -> view.addResultsToList(friends.toMutableList()) }, { throwable -> view.handleError(throwable) })
    }

    override fun searchByTag(tagName: String) {
        this.currentOperation = OPERATION_TAG
        this.tagName = tagName

        tearDown()

        friendSubscription = friendRepository.getFriendsFromTagName(tagName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friends ->
                    view.addResultsToList(friends.toMutableList()) }, { throwable -> view.handleError(throwable) })
    }

    override fun tearDown() {
        if (friendSubscription?.isDisposed() ?: false)
            friendSubscription?.dispose()
    }

    override fun restoreData() {
        if(currentOperation == null) return
        when (currentOperation) {
            OPERATION_ALL -> search()
            OPERATION_QUERY -> search(query)
            OPERATION_TAG -> searchByTag(tagName)
            else -> search()
        }
    }
}