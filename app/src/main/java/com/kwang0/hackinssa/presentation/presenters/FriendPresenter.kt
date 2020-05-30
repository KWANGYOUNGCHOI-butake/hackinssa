package com.kwang0.hackinssa.presentation.presenters


interface FriendPresenter {
    fun search()
    fun search(query: String)
    fun searchByTag(tagName: String)
    fun tearDown()
    fun restoreData()
}