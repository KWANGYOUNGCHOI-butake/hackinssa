package com.kwang0.hackinssa.data.remote

import com.kwang0.hackinssa.data.models.Country
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface CountryRepositoryRemote {

    @GET("all")
    fun getAll(): Observable<List<Country?>?>?

    @GET("name/{name}?fullText=true")
    fun getByName( @Path("name") name: String? ): Observable<List<Country?>?>?

    @GET("name/{name}")
    fun search( @Path("name") name: String? ): Observable<List<Country?>?>?

    @GET("region/{region}")
    fun getByRegion( @Path("region") region: String? ): Observable<List<Country?>?>?

    companion object { const val BASE_URL = "https://restcountries.eu/rest/v2/" }
}