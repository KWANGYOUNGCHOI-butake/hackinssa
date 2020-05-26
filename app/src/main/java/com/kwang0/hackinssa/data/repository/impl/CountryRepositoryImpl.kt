package com.kwang0.hackinssa.data.repository.impl

import com.kwang0.hackinssa.data.models.Country

import com.kwang0.hackinssa.data.remote.CountryRepositoryRemote

import com.kwang0.hackinssa.data.repository.CountryRepository
import io.reactivex.Flowable
import io.reactivex.Observable


class CountryRepositoryImpl(private val countryRepositoryRemote: CountryRepositoryRemote) : CountryRepository {
    override fun getAll(): Flowable<MutableList<Country?>?>? {
        return countryRepositoryRemote.getAll()
    }

    override fun getByLang(et: String?): Flowable<MutableList<Country?>?>? {
        return countryRepositoryRemote.getByLang(et)
    }

    override fun getByName(name: String?): Flowable<MutableList<Country?>?>? {
        return countryRepositoryRemote.getByName(name)
    }

    override fun getByCalling(calling: Int?): Flowable<MutableList<Country?>?>? {
        return countryRepositoryRemote.getByCalling(calling)
    }

}