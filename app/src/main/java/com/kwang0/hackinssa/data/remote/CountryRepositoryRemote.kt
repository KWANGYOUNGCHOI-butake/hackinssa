package com.kwang0.hackinssa.data.remote

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface CountryRepositoryRemote {

    @GET("all")
    fun getAll(): Flowable<MutableList<Country?>?>?

    @GET("name/{name}?fullText=true")
    fun getByName( @Path("name") name: String? ): Flowable<MutableList<Country?>?>?

    @GET("name/{name}")
    fun search( @Path("name") name: String? ): Flowable<MutableList<Country?>?>?

    @GET("region/{region}")
    fun getByRegion( @Path("region") region: String? ): Flowable<MutableList<Country?>?>?

    companion object { const val BASE_URL = "https://restcountries.eu/rest/v2/" }
}