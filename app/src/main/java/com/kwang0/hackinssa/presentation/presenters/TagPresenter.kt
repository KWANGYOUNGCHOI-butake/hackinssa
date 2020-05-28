package com.kwang0.hackinssa.presentation.presenters

interface TagPresenter {
    fun search(query: String?)
    fun clear()
    fun restoreData()
}