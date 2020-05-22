package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Country

interface CountryPresenterView {
    fun adapterNotifyChanges()
    fun addResultsToList(countries: MutableList<Country?>?)
    fun handleError(throwable: Throwable?)
}