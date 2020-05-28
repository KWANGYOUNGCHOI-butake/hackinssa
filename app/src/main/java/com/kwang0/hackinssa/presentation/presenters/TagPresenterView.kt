package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Tag

interface TagPresenterView {
    fun adapterNotifyChanges()
    fun addResultsToList(tags: MutableList<Tag>)
    fun handleEmpty()
    fun handleError(throwable: Throwable?)
}