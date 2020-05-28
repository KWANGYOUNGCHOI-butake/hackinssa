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
    private var mDisposable = CompositeDisposable()

    private var query: String? = null

    init {
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }

    override fun search(query: String?) {
        this.query = query

        mDisposable.add(tagRepository.getTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tags ->
                    view.addResultsToList(tags.toMutableList()) }, { throwable -> view.handleError(throwable) }))
    }

    override fun clear() {
        view.handleEmpty()
    }

    override fun restoreData() {
        search(query)
    }
}