package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Country

interface CountryPresenterView {
    fun addResultsToList(countries: MutableList<Country?>?)
    fun handleEmpty()
    fun handleError(throwable: Throwable?)
}