package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Country

interface CountryContract {
    interface Presenter {
        fun search(query: String)
        fun tearDown()
        fun clear()
        fun restoreData()
    }

    interface View {
        fun addResultsToList(countries: List<Country>)
        fun handleEmpty()
        fun handleError(throwable: Throwable?)
    }
}