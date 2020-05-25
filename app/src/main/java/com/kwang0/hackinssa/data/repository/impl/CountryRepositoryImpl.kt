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

    override fun getByName(name: String?): Flowable<MutableList<Country?>?>? {
        return countryRepositoryRemote.getByName(name)
    }

    override fun search(query: String?): Flowable<MutableList<Country?>?>? {
        return countryRepositoryRemote.search(query)
    }

    override fun getByRegion(region: String?): Flowable<MutableList<Country?>?>? {
        return countryRepositoryRemote.getByRegion(region)
    }

}