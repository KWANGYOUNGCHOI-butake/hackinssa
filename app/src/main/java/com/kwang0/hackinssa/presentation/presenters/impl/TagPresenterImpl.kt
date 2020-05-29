package com.kwang0.hackinssa.presentation.presenters.impl

import android.content.Context
import com.kwang0.hackinssa.data.dao.impl.TagDaoImpl
import com.kwang0.hackinssa.data.repository.TagRepository
import com.kwang0.hackinssa.data.repository.impl.TagRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.TagPresenter
import com.kwang0.hackinssa.presentation.presenters.TagPresenterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TagPresenterImpl(context: Context, private var view: TagPresenterView): TagPresenter {
    private val TAG = TagPresenterImpl::class.simpleName

    private var tagRepository: TagRepository
    private var tagSubscription: Disposable? = null
    private var mDisposable = CompositeDisposable()

    private var tagName: String = ""

    init {
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }

    override fun searchByTagName(tagName: String) {
        this.tagName = tagName

        mDisposable.add(tagRepository.getTagByName(tagName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tags ->
                    view.addResultsToList(tags.toMutableList().sortedBy { it.tagCreated }
                            .distinctBy { it.tagName }
                            .toMutableList()) }, { throwable -> view.handleError(throwable) }))
    }

    override fun deleteByTagNames(tagNames: List<String>) {
        tagSubscription = tagRepository.deleteTagByNames(tagNames)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.finishDelete() }, { throwable -> view.handleError(throwable) })
    }


    override fun clear() {
        view.handleEmpty()
    }

    override fun restoreData() {
        searchByTagName(tagName)
    }
}