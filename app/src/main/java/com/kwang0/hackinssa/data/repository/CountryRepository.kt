package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Flowable
import io.reactivex.Observable

interface CountryRepository {
    fun getAll(): Flowable<MutableList<Country?>?>?
    fun getByLang(et: String?): Flowable<MutableList<Country?>?>?
    fun getByName(name: String?): Flowable<MutableList<Country?>?>?
    fun getByCalling(calling: Int?): Flowable<MutableList<Country?>?>?
}