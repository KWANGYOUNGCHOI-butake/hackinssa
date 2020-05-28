package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Flowable
import io.reactivex.Observable

interface CountryRepository {
    fun getAll(): Flowable<List<Country>>
    fun getByLang(et: String?): Flowable<List<Country>>
    fun getByName(name: String?): Flowable<List<Country>>
    fun getByCalling(calling: Int?): Flowable<List<Country>>
}