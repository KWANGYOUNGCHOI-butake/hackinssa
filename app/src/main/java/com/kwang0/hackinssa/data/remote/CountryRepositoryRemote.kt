package com.kwang0.hackinssa.data.remote

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface CountryRepositoryRemote {

    @GET("all")
    fun getAll(): Flowable<MutableList<Country?>?>?

    @GET("lang/{et}")
    fun getByLang( @Path("et") et: String? ): Flowable<MutableList<Country?>?>?

    @GET("name/{name}")
    fun getByName( @Path("name") name: String? ): Flowable<MutableList<Country?>?>?

    @GET("callingcode/{callingcode}")
    fun getByCalling( @Path("callingcode") calling: Int? ): Flowable<MutableList<Country?>?>?

    companion object { const val BASE_URL = "https://restcountries.eu/rest/v2/" }
}