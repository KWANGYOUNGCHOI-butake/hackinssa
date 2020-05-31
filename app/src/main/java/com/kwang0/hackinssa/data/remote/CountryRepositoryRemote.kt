package com.kwang0.hackinssa.data.remote

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface CountryRepositoryRemote {

    @GET("all")
    fun getAll(): Flowable<List<Country>>

    @GET("lang/{et}")
    fun getByLang( @Path("et") et: String? ): Flowable<List<Country>>

    @GET("name/{name}")
    fun getByName( @Path("name") name: String? ): Flowable<List<Country>>

    @GET("callingcode/{callingcode}")
    fun getByCalling( @Path("callingcode") calling: Int? ): Flowable<List<Country>>

}