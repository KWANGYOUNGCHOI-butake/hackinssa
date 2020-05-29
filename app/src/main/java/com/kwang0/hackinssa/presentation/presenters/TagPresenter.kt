package com.kwang0.hackinssa.presentation.presenters

interface TagPresenter {
    fun searchByTagName(tagName: String)
    fun deleteByTagNames(tagNames: List<String>)
    fun clear()
    fun restoreData()
}