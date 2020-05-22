package com.kwang0.hackinssa.presentation.presenters

interface CountryPresenter {
    fun setUp()
    fun getAll()

    fun search(query: String?)
    fun getByRegion(region: String?)
    fun restoreData()
}