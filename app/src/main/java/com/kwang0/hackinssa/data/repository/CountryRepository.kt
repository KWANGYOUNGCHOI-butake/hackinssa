package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Observable

interface CountryRepository {
    fun getAll(): Observable<MutableList<Country?>?>?
    fun getByName(name: String?): Observable<MutableList<Country?>?>?
    fun search(query: String?): Observable<MutableList<Country?>?>?
    fun getByRegion(region: String?): Observable<MutableList<Country?>?>?
}