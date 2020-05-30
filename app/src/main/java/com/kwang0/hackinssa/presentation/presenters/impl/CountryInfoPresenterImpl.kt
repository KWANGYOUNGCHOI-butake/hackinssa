package com.kwang0.hackinssa.presentation.presenters.impl

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.dao.impl.FavoriteDaoImpl
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Favorite
import com.kwang0.hackinssa.data.repository.FavoriteRepository
import com.kwang0.hackinssa.data.repository.impl.FavoriteRepositoryImpl
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.presenters.CountryInfoPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryInfoPresenterView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.fixedRateTimer

class CountryInfoPresenterImpl(private val activity: Activity, private var view: CountryInfoPresenterView): CountryInfoPresenter {
    private val TAG = CountryInfoPresenterImpl::class.simpleName

    private var favoriteRepository: FavoriteRepository
    private val mDisposable = CompositeDisposable()

    private var country: Country? = null
    private var favorite: Favorite? = null

    init {
        favoriteRepository = FavoriteRepositoryImpl(FavoriteDaoImpl(activity))
    }

    override fun onCreate() {
        try {
            getIntentExtra(activity.intent)
        } catch (e: Exception) {
            Log.e(TAG, "Get Exception : " + e.message)
        }
    }

    override fun onCreateStarMenu(countryName: String) {
        mDisposable.add(isFavorite( countryName )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ b ->
                    view.notifyFavoriteChange(b)
                }, { throwable -> Log.e(TAG, "Unable to get favorite", throwable) }))
    }

    override fun onStop() {
        mDisposable.clear()
    }

    override fun onFavoriteChange(countryName: String) {
        view.starBtnDisable()
        mDisposable.add(insertOrUpdateFavorite( countryName )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.starBtnEnable() },
                        { throwable -> Log.e(TAG, "Unable to insert or update favorite", throwable) }))
    }

    override fun onFriendAddSelect(country: Country?) {
        view.startFriendAddAct(country)
    }

    @Throws(Exception::class)
    fun getIntentExtra(intent: Intent?) {
        country = intent?.extras?.getSerializable("country") as? Country

        country?.let {
            view.setCountryFlag(it.getAlpha2Code())
            view.setNameText(it.getNativeName())
            getLocaleTime(it.getTimezones().get(0))
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getLocaleTime(timeZone: String?) {
        mDisposable.add(Flowable.timer(5000, TimeUnit.MILLISECONDS)
                .repeat() //to perform your task every 5 seconds
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss (XXX)")
                    sdf.setTimeZone(TimeZone.getTimeZone(timeZone?.replace("UTC", "GMT")))
                    view.setTimeText(sdf.format(Date()))
                })
    }

    fun isFavorite(favoriteName: String): Flowable<Boolean> {
        return favoriteRepository.getFavorite(favoriteName)
                .map({ favorite ->
                    this.favorite = favorite
                    favorite.isFavorite
                })
    }

    fun insertOrUpdateFavorite(favoriteName: String): Completable {
        if(this.favorite == null) {
            return favoriteRepository.insertFavorite(Favorite(favoriteName, true))
        } else {
            favorite?.isFavorite = if(favorite?.isFavorite == true) false else true
            return favoriteRepository.updateFavorite(favorite!!)
        }
    }
}