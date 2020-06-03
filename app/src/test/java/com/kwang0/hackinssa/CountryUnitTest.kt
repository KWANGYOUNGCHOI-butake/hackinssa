package com.kwang0.hackinssa

import com.kwang0.hackinssa.data.RetrofitApi.Companion.getInstance
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.remote.impl.CountryRepositoryRemoteImpl
import com.kwang0.hackinssa.data.repository.CountryRepository
import com.kwang0.hackinssa.data.repository.impl.CountryRepositoryImpl
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import java9.util.stream.Stream
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.After
import org.junit.Before
import org.junit.Test


class CountryUnitTest {
    var countryRepository: CountryRepository? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        countryRepository = CountryRepositoryImpl(CountryRepositoryRemoteImpl())
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        countryRepository = null
    }

    // Adding sleep to avoid HTTP 429 when running all tests
    @Test
    @Throws(Exception::class)
    fun getAllCountries() {
        // Adding sleep to avoid HTTP 429 when running all tests
        Thread.sleep(3000)
        val testSubscriber: TestSubscriber<List<Country>> = TestSubscriber()
        countryRepository?.getAll()
                ?.subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        assertEquals(testSubscriber.valueCount() > 0, true)
        testSubscriber.values().forEach { countries: List<Country> -> assertCountries(countries) }

    }

    fun assertCountries(countries: List<Country>) {
        for (country in countries) {
            System.out.println("name : " + country.getName())
            assertFalse(country.getName().isEmpty())
            assertFalse(country.getAlpha2Code().isEmpty())
        }
    }
}