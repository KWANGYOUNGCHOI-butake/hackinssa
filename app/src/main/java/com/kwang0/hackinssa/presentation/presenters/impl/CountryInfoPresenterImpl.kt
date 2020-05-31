package com.kwang0.hackinssa.presentation.presenters.impl

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
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

class CountryInfoPresenterImpl(private val context: Context, private var view: CountryInfoPresenterView): CountryInfoPresenter {
    private val TAG = CountryInfoPresenterImpl::class.simpleName

    private var favoriteRepository: FavoriteRepository
    private val mDisposable = CompositeDisposable()

    private var country: Country? = null
    private var favorite: Favorite? = null

    init {
        favoriteRepository = FavoriteRepositoryImpl(FavoriteDaoImpl(context))
    }

    override fun onCreate() {
        try {
            getIntentExtra((context as? Activity)?.intent)
        } catch (e: Exception) {
            Log.e(TAG, "Get Exception : " + e.message)
        }
    }

    override fun onStart() {
        country?.let {
            try {
                getLocaleTime(it.getTimezones().get(0))
            } catch (e: IndexOutOfBoundsException) {
                Toast.makeText(context, context.getString(R.string.exception_out_of_bounds), Toast.LENGTH_LONG).show()
            }
            mDisposable.add(isFavorite( it.getName() )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ b -> view.notifyFavoriteChange(b) },
                            { throwable -> view.handleError(throwable) }))
        }
    }

    override fun onStop() {
        mDisposable.clear()
    }

    // star 버튼을 누르면 작동함
    // 이전 페이지에서 받아온 국가 정보를 가지고 favorite 체크를 해줌
    override fun onFavoriteChange() {
        view.starBtnDisable()
        country?.let {
            mDisposable.add(insertOrUpdateFavorite( it.getName() )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ view.starBtnEnable() },
                            { throwable -> view.handleError(throwable) })) }
    }

    // 친구추가 버튼 선택시 작동
    override fun onFriendAddSelect() {
        view.startFriendAddAct(country)
    }

    // 액티비티 시작시 호출
    fun getIntentExtra(intent: Intent?) {
        country = intent?.extras?.getSerializable("country") as? Country

        country?.let {
            view.setCountryFlag(it.getAlpha2Code())
            view.setNameText(it.getNativeName())
        }
    }

    // 현재시간을 기반으로 1초 마다 새로운 시간을 textview 에 반영시켜 줌
    // 네트워크로 시간을 받아서 반영 시켜 줄려고 했으나, 현재 기기 시간과 화면에 보여주는 시간에 오차가 있을 때
    // 유저에게 불편함을 야기 수 있다고 생각되어 로컬시간을 기반으로 포맨에 맞는 시간을 출력 시킴
    @SuppressLint("SimpleDateFormat")
    private fun getLocaleTime(timeZone: String?) {
        mDisposable.add(Flowable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss (XXX)")
                    sdf.setTimeZone(TimeZone.getTimeZone(timeZone?.replace("UTC", "GMT")))
                    view.setTimeText(sdf.format(Date()))
                })
    }

    // string 값으로 받아온 favorite 데이터의 isFavorite 값을 return
    private fun isFavorite(favoriteName: String): Flowable<Boolean> {
        return favoriteRepository.getFavorite(favoriteName)
                .map { favorite ->
                    this.favorite = favorite
                    favorite.isFavorite
                }
    }

    private fun insertOrUpdateFavorite(favoriteName: String): Completable {
        if(this.favorite == null) {
            return favoriteRepository.insertFavorite(Favorite(favoriteName, true))
        } else {
            favorite?.isFavorite = if(favorite?.isFavorite == true) false else true
            return favoriteRepository.updateFavorite(favorite!!)
        }
    }
}