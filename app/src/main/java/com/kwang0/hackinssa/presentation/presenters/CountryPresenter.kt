package com.kwang0.hackinssa.presentation.presenters

interface CountryPresenter {
    fun search(query: String)
    fun tearDown()
    fun clear()
    fun restoreData()
}