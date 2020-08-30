package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Tag

interface TagContract {
    interface Presenter {
        fun searchByTagName(tagName: String)
        fun deleteByTagNames(tagNames: List<String>)
        fun tearDown()
        fun clear()
        fun restoreData()
    }

    interface View {
        fun addResultsToList(tags: MutableList<Tag>)
        fun finishDelete()
        fun handleEmpty()
        fun handleError(throwable: Throwable?)
    }
}