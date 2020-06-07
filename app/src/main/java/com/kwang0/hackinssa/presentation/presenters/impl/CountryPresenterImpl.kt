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

class CountryPresenterImpl(private var view: CountryPresenterView) : CountryPresenter {
    private val TAG = CountryPresenterImpl::class.simpleName
    private val OPERATION_CLEAR = 0
    private val OPERATION_QUERY = 1

    private var countryRepository: CountryRepository
    private var countryDisposable: Disposable? = null

    private var currentOperation: Int? = null
    private var query: String = ""

    init {
        countryRepository = CountryRepositoryImpl(CountryRepositoryRemoteImpl())
    }

    // 입력값을 기반으로 국가 정보 검색 (영어 이름, 국가 코드, 국가 번호)
    override fun search(query: String) {
        this.query = query
        this.currentOperation = OPERATION_QUERY

        tearDown()

        val langRequest = countryRepository.getByLang(query).onErrorReturn { e -> listOf<Country>() }
        val nameRequest = countryRepository.getByName(query).onErrorReturn { e -> listOf<Country>() }
        val callingRequest = countryRepository.getByCalling(query.toIntOrNull() ?: - 1).onErrorReturn { e -> listOf<Country>() }

        countryDisposable = Flowable.zip(langRequest, nameRequest, callingRequest,
                Function3<List<Country>, List<Country>, List<Country>, List<Country>> {
                    lang, name, calling ->
                    val allCountries = lang.toMutableList().plus(name.toMutableList()).plus(calling.toMutableList())
                    return@Function3 allCountries.distinctBy { it.getName() }.sortedBy { it.getName() }.toMutableList()
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ countries -> view.addResultsToList(countries) }, { throwable -> view.handleError(throwable) })
    }

    // 해제 작업
    override fun tearDown() {
        if(countryDisposable?.isDisposed?.not() == true)
            countryDisposable?.dispose()
    }

    // 비어있는 페이지 호출
    override fun clear() {
        this.currentOperation = OPERATION_CLEAR
        query = ""
        view.handleEmpty()
    }

    // 데이터 복구
    override fun restoreData() {
        if(currentOperation == null) return
        when (currentOperation) {
            OPERATION_CLEAR -> clear()
            OPERATION_QUERY -> search(query)
            else -> clear()
        }

    }
}