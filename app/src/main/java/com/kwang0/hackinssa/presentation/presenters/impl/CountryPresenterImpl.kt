package com.kwang0.hackinssa.presentation.presenters.impl

import android.util.Log
import com.kwang0.hackinssa.data.remote.impl.CountryRepositoryRemoteImpl
import com.kwang0.hackinssa.data.repository.CountryRepository
import com.kwang0.hackinssa.data.repository.impl.CountryRepositoryImpl
import com.kwang0.hackinssa.presentation.presenters.CountryPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryPresenterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CountryPresenterImpl(view: CountryPresenterView) : CountryPresenter {
    private val TAG = CountryPresenterImpl::class.simpleName

    private var view: CountryPresenterView? = view
    private var countryRepository: CountryRepository? = null
    private var countrySubscription: Disposable? = null

    private val OPERATION_QUERY = 0

    private var currentOperation = 0
    private var query: String? = null
    private var region: String? = null

    fun createPresenter(view: CountryPresenterView): CountryPresenterImpl? {
        return CountryPresenterImpl(view)
    }

    override fun setUp() {
        countryRepository = CountryRepositoryImpl(CountryRepositoryRemoteImpl())
    }

    override fun search(query: String?) {
        currentOperation = OPERATION_QUERY
        this.query = query
        countrySubscription = countryRepository?.search(query)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnComplete({ view!!.adapterNotifyChanges() })
                ?.subscribe({ countries -> view!!.addResultsToList(countries) }, { throwable -> view!!.handleError(throwable) })
    }

    override fun restoreData() {
        when (currentOperation) {
            OPERATION_QUERY -> search(query)
            else -> search(query)
        }
    }
}