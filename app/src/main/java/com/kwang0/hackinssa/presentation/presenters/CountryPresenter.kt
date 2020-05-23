package com.kwang0.hackinssa.presentation.presenters

interface CountryPresenter {
    fun setUp()

    fun search(query: String?)
    fun restoreData()
}