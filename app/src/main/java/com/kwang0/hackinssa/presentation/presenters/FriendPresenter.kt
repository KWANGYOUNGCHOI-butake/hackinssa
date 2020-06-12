package com.kwang0.hackinssa.presentation.presenters


interface FriendPresenter {
    fun search()
    fun search(query: String)
    fun searchByTag(tagName: String)
    fun deleteFriend(friendId: String)
    fun tearDown()
    fun restoreData()
}