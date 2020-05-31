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
    private val OPERATION_QUERY = 0
    private val OPERATION_DELETE = 1

    private var tagRepository: TagRepository
    private var tagSubscription: Disposable? = null

    private var currentOperation: Int? = null
    private var tagName: String = ""
    private var tagNames: List<String> = listOf()

    init {
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }

    override fun searchByTagName(tagName: String) {
        this.currentOperation = OPERATION_QUERY
        this.tagName = tagName

        tearDown()

        tagSubscription = tagRepository.getTagByName(tagName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tags ->
                    view.addResultsToList(tags.toMutableList().sortedBy { it.tagCreated }
                            .distinctBy { it.tagName }
                            .toMutableList()) }, { throwable -> view.handleError(throwable) })
    }

    override fun deleteByTagNames(tagNames: List<String>) {
        this.currentOperation = OPERATION_DELETE
        this.tagNames = tagNames

        tearDown()

        tagSubscription = tagRepository.deleteTagByNames(tagNames)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.finishDelete() }, { throwable -> view.handleError(throwable) })
    }

    override fun tearDown() {
        if (tagSubscription?.isDisposed?.not() == true)
            tagSubscription?.dispose()
    }


    override fun clear() {
        view.handleEmpty()
    }

    override fun restoreData() {
        if(currentOperation == null) return
        when (currentOperation) {
            OPERATION_QUERY -> searchByTagName(tagName)
            OPERATION_DELETE -> deleteByTagNames(tagNames)
            else -> searchByTagName(tagName)
        }
    }
}