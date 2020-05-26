package com.kwang0.hackinssa.presentation.presenters

interface CountryPresenter {
    fun search(query: String?)
    fun clear()
    fun restoreData()
}