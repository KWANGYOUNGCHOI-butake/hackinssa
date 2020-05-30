package com.kwang0.hackinssa.presentation.presenters.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
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

class FriendInfoPresenterImpl(private val context: Context, private var view: FriendInfoPresenterView): FriendInfoPresenter {
    private val TAG = FriendInfoPresenterImpl::class.simpleName

    private var friendRepository: FriendRepository
    private var tagRepository: TagRepository
    private var mDisposable = CompositeDisposable()

    var friend: Friend? = null
    var tags = Tags(ArrayList<Tag>())

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(context))
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }

    override fun onCreate() {
        try {
            getIntentExra((context as? Activity)?.intent)
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "Get Exception : " + e.message)
        }
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
        IntentHelper.phoneIntent(context, friend?.friendPhone)
    }

    override fun onEmailSelect() {
        IntentHelper.emailIntent(context, friend?.friendEmail)
    }

    override fun onEditSelect() {
        val intent = Intent(context, FriendAddActivity::class.java)
        intent.putExtra("friend", friend)
        intent.putExtra("tags", tags)
        context.startActivity(intent)
    }

    private fun getIntentExra(intent: Intent?) {
        friend = intent?.extras?.getSerializable("friend") as? Friend
    }

    private fun addGetFriendFlow(friendId: String) {
        mDisposable.add(friendRepository.getFriend( friendId )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friend ->
                    this.friend = friend
                    view.addFriendResult(friend) },
                        { throwable -> view.handleError(throwable) }) )
    }

    private fun addGetTagsFlow(friendId: String) {
        mDisposable.add(tagRepository.getTagById( friendId )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tags ->
                    this.tags.tagList = tags.toMutableList()
                    view.addTagResultsToList(tags) },
                        { throwable -> view.handleError(throwable) }))
    }
}