package com.kwang0.hackinssa

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.models.Favorite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch


@RunWith(AndroidJUnit4::class)
@MediumTest
class FavoriteEntityReadWriteTest {
    private val TAG = FavoriteEntityReadWriteTest::class.simpleName
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var db: InssaDatabase

    @Before
    fun create_db() {
        System.out.println("테스트 시작")
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, InssaDatabase::class.java).build()
        favoriteDao = db.favoriteDao()
    }

    @After
    @Throws(IOException::class)
    fun close_db() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun write_favorite_and_read() {
        val latch = CountDownLatch(1)
        val favorite: Favorite = Favorite("young", false)
        favoriteDao.insertFavorite(favorite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ System.out.println("insert finished") },
                        { throwable ->
                            System.out.println("thorwable : " + throwable.message)
                            assertEquals(true, false)})
        favoriteDao.getFavorite("young").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ f ->
                    System.out.println(f?.name)
                    assertThat(f?.name, equalTo(favorite.name))
                    latch.countDown() },
                        { throwable -> System.out.println("thorwable : " + throwable.message)
                            assertEquals(true, false)})

        latch.await()
    }
}
