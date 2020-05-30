package com.kwang0.hackinssa.presentation.presenters.impl

import android.util.Log
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.remote.impl.CountryRepositoryRemoteImpl
import com.kwang0.hackinssa.data.repository.CountryRepository
import com.kwang0.hackinssa.data.repository.impl.CountryRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.CountryPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryPresenterView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import java9.util.stream.Stream
import java9.util.stream.StreamSupport

class CountryPresenterImpl(private var view: CountryPresenterView) : CountryPresenter {
    private val TAG = CountryPresenterImpl::class.simpleName

    private var countryRepository: CountryRepository
    private var countrySubscription: Disposable? = null

    private var query: String = ""

    init {
        countryRepository = CountryRepositoryImpl(CountryRepositoryRemoteImpl())
    }

    override fun search(query: String) {
        this.query = query

        val langRequest = countryRepository.getByLang(query).onErrorReturn { e -> listOf<Country>() }
        val nameRequest = countryRepository.getByName(query).onErrorReturn { e -> listOf<Country>() }
        val callingRequest = countryRepository.getByCalling(query.toIntOrNull() ?: - 1).onErrorReturn { e -> listOf<Country>() }

        countrySubscription = Flowable.zip(langRequest, nameRequest, callingRequest,
                Function3<List<Country>, List<Country>, List<Country>, List<Country>> {
                    lang, name, calling ->
                    val allCountries = lang.toMutableList().plus(name.toMutableList()).plus(calling.toMutableList())
                    return@Function3 allCountries.distinctBy { it.getName() }.sortedBy { it.getName() }.toMutableList()
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ countries -> view.addResultsToList(countries) }, { throwable -> view.handleError(throwable) })
    }

    override fun clear() {
        view.handleEmpty()
    }

    override fun restoreData() {
        search(query)
    }
}