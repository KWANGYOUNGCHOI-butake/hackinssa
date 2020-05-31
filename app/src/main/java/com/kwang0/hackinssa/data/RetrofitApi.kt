package com.kwang0.hackinssa.data

import android.content.Context
import com.kwang0.hackinssa.data.remote.CountryRepositoryRemote
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitApi {

    companion object {
        const val BASE_URL = "https://restcountries.eu/rest/v2/"

        // 멀티 쓰레드 환경에서 발생하는 문제를 해결하기 위해
        // volatile 을 사용해서 변수의 read와 write를 Main Memory에서 진행
        @Volatile
        private var retrofit : Retrofit? = null

        @Synchronized
        fun getInstance(): Retrofit =
                retrofit ?: synchronized(this) {
                    retrofit ?: buildRetrofit().also { retrofit = it }
                }

        private fun buildRetrofit() = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

    }
}