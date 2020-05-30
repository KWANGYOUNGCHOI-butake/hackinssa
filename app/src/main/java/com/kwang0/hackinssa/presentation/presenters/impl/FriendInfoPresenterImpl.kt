package com.kwang0.hackinssa.presentation.presenters.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.kwang0.hackinssa.data.dao.impl.FriendDaoImpl
import com.kwang0.hackinssa.data.dao.impl.TagDaoImpl
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.models.Tags
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.TagRepository
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.data.repository.impl.TagRepositoryImpl
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.presentation.presenters.FriendInfoPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendInfoPresenterView
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FriendInfoPresenterImpl(private val activity: Activity, private var view: FriendInfoPresenterView): FriendInfoPresenter {
    private val TAG = FriendInfoPresenterImpl::class.simpleName

    private var friendRepository: FriendRepository
    private var tagRepository: TagRepository
    private var mDisposable = CompositeDisposable()

    var friend: Friend? = null
    var tags = Tags(ArrayList<Tag>())

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(activity))
        tagRepository = TagRepositoryImpl(TagDaoImpl(activity))
    }

    override fun onCreate() {
        getIntentExra(activity.intent)
    }

    override fun onStart() {
        friend?.friendId?.let {
            addGetFriendFlow(it)
            addGetTagsFlow(it)
        }
    }

    override fun onStop() {
        view.clearTags()
        mDisposable.clear()
    }

    override fun onPhoneSelect() {
        IntentHelper.phoneIntent(activity, friend?.friendPhone)
    }

    override fun onEmailSelect() {
        IntentHelper.emailIntent(activity, friend?.friendEmail)
    }

    override fun onEditSelect() {
        val intent = Intent(activity, FriendAddActivity::class.java)
        intent.putExtra("friend", friend)
        intent.putExtra("tags", tags)
        activity.startActivity(intent)
    }

    @Throws(Exception::class)
    fun getIntentExra(intent: Intent?) {
        friend = intent?.extras?.getSerializable("friend") as? Friend
    }

    fun addGetFriendFlow(friendId: String) {
        mDisposable.add(friendRepository.getFriend( friendId )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friend ->
                    this.friend = friend
                    view.addFriendResult(friend) },
                        { throwable -> view.handleError(throwable) }) )
    }

    fun addGetTagsFlow(friendId: String) {
        mDisposable.add(tagRepository.getTagById( friendId )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tags ->
                    this.tags.tagList = tags.toMutableList()
                    view.addTagResultsToList(tags) },
                        { throwable -> view.handleError(throwable) }))
    }
}