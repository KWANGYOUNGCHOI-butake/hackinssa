package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Tag

interface TagPresenterView {
    fun addResultsToList(tags: MutableList<Tag>)
    fun finishDelete()
    fun handleEmpty()
    fun handleError(throwable: Throwable?)
}