package com.kwang0.hackinssa.presentation.presenters.impl

import android.content.Context
import com.kwang0.hackinssa.data.dao.impl.TagDaoImpl
import com.kwang0.hackinssa.data.repository.TagRepository
import com.kwang0.hackinssa.data.repository.impl.TagRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.TagPresenter
import com.kwang0.hackinssa.presentation.presenters.TagPresenterView
import io.reactivex.disposables.Disposable

class TagPresenterImpl(context: Context, view: TagPresenterView): TagPresenter {
    private val TAG = TagPresenterImpl::class.simpleName

    private var view: TagPresenterView? = view
    private var tagRepository: TagRepository? = null
    private var tagSubscription: Disposable? = null

    init {
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }

    override fun search(query: String?) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun restoreData() {
        TODO("Not yet implemented")
    }
}