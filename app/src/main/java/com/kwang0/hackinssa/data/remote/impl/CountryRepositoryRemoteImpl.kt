package com.kwang0.hackinssa.data.remote.impl

import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.remote.CountryRepositoryRemote
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CountryRepositoryRemoteImpl() : CountryRepositoryRemote{

    private var retrofit: Retrofit? = null
    private var countryService: CountryRepositoryRemote? = null

    init {
        setUpRetrofit()
        createServices()
    }

    override fun getAll(): Flowable<MutableList<Country?>?>? {
        return countryService?.getAll()
    }

    override fun getByLang(et: String?): Flowable<MutableList<Country?>?>? {
        return countryService?.getByLang(et)
    }

    override fun getByName(name: String?): Flowable<MutableList<Country?>?>? {
        return countryService?.getByName(name)
    }

    override fun getByCalling(calling: Int?): Flowable<MutableList<Country?>?>? {
        return countryService?.getByCalling(calling)
    }

    private fun setUpRetrofit() {
        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CountryRepositoryRemote.BASE_URL)
                .build()
    }

    private fun createServices() {
        countryService = retrofit?.create(CountryRepositoryRemote::class.java)
    }
}