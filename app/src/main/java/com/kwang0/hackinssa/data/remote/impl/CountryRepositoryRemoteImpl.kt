package com.kwang0.hackinssa.data.remote.impl

import com.kwang0.hackinssa.data.RetrofitApi
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.remote.CountryRepositoryRemote
import io.reactivex.Flowable


class CountryRepositoryRemoteImpl() : CountryRepositoryRemote{

    private var countryService: CountryRepositoryRemote

    init {
        countryService = RetrofitApi.getInstance().create(CountryRepositoryRemote::class.java)
    }

    override fun getAll(): Flowable<List<Country>> {
        return countryService.getAll()
    }

    override fun getByLang(et: String?): Flowable<List<Country>> {
        return countryService.getByLang(et)
    }

    override fun getByName(name: String?): Flowable<List<Country>> {
        return countryService.getByName(name)
    }

    override fun getByCalling(calling: Int?): Flowable<List<Country>> {
        return countryService.getByCalling(calling)
    }
}