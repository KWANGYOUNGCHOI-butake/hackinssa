package com.kwang0.hackinssa.data.remote.impl

import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.remote.CountryRepositoryRemote
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CountryRepositoryRemoteImpl: CountryRepositoryRemote{

    private var retrofit: Retrofit? = null
    private var countryService: CountryRepositoryRemote? = null

    fun CountryRepositoryRemoteImpl() {
        setUpRetrofit()
        createServices()
    }

    override fun getAll(): Observable<List<Country?>?>? {
        return countryService!!.getAll()
    }

    override fun getByName(name: String?): Observable<List<Country?>?>? {
        return countryService!!.getByName(name)
    }

    override fun search(name: String?): Observable<List<Country?>?>? {
        return countryService!!.search(name)
    }

    override fun getByRegion(region: String?): Observable<List<Country?>?>? {
        return countryService!!.getByRegion(region)
    }

    private fun setUpRetrofit() {
        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CountryRepositoryRemote.BASE_URL)
                .build()
    }

    private fun createServices() {
        countryService = retrofit!!.create(CountryRepositoryRemote::class.java)
    }
}