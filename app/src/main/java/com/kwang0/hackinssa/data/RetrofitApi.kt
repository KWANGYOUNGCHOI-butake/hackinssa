package com.kwang0.hackinssa.data

import android.content.Context
import androidx.room.Room
import com.kwang0.hackinssa.data.remote.CountryRepositoryRemote
import com.kwang0.hackinssa.helper.SingletonHolder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// retrofit singleton
abstract class RetrofitApi {
    companion object : SingletonHolder<Retrofit>({
        val BASE_URL = "https://restcountries.eu/rest/v2/"

        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    })
}