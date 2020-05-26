package com.kwang0.hackinssa.presentation.ui.activities.countryinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.helper.PicassoHelper
import com.kwang0.hackinssa.presentation.presenters.FavoritePresenter
import com.kwang0.hackinssa.presentation.presenters.FavoritePresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.FavoritePresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class CountryInfoActivity : BaseActivity(), FavoritePresenterView {
    val TAG = CountryInfoActivity::class.simpleName

    var country: Country? = null

    lateinit private var favoritePresenter: FavoritePresenter
    private val mDisposable = CompositeDisposable()
    private var menu: Menu? = null
    private var isFavorite = false

    lateinit var toolbar: Toolbar
    lateinit var iv: ImageView
    lateinit var name_tv: TextView
    lateinit var time_tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        iv = findViewById<ImageView>(R.id.ci_iv)
        name_tv = findViewById<TextView>(R.id.ci_name_tv)
        time_tv = findViewById<TextView>(R.id.ci_time_tv)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        getIntentExtra(intent)

        favoritePresenter = FavoritePresenterImpl(this, this)
    }


    override fun onStart() {
        super.onStart()
        mDisposable.add(favoritePresenter.isFavorite(country?.getName()!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ chk ->
                    if(chk) {
                        menu?.getItem(0)?.setIcon(R.drawable.ic_star_fill)
                    } else {
                        menu?.getItem(0)?.setIcon(R.drawable.ic_star_border)
                    }

                }, { throwable -> Log.e(TAG, "Unable to get username", throwable) })!!)
    }

    override fun onStop() {
        super.onStop()

        // clear all the subscriptions
        mDisposable.clear()
    }

    fun getIntentExtra(intent: Intent?) {
        country = intent?.extras?.getSerializable("country") as? Country

        country?.let {
            PicassoHelper.loadImg(CountryAdapter.BASE_IMG_URL_250_PX.toString() + it.getAlpha2Code()!!.toLowerCase() + ".png?raw=true", iv)
            name_tv.text = it.getNativeName()
            getLocaleTime(it.getTimezones()?.get(0))
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getLocaleTime(timeZone: String?) {
        fixedRateTimer("timer", false, 0L, 1000) {
            this@CountryInfoActivity.runOnUiThread {
                val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss (XXX)")
                sdf.setTimeZone(TimeZone.getTimeZone(timeZone?.replace("UTC", "GMT")))
                time_tv.text = sdf.format(Date())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_country_info, menu)
        mDisposable.add(favoritePresenter.isFavorite(country?.getName()!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ chk ->
                    if(chk) {
                        menu?.getItem(0)?.setIcon(R.drawable.ic_star_fill)
                    } else {
                        menu?.getItem(0)?.setIcon(R.drawable.ic_star_border)
                    }

                }, { throwable -> Log.e(TAG, "Unable to get username", throwable) })!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_ci_star) {
            if(isFavorite) {
                item.setEnabled(false)
                mDisposable.add(favoritePresenter.deleteFavorite(country?.getName()!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ item.setEnabled(true) },
                                { throwable -> Log.e(TAG, "Unable to update username", throwable) }))
            } else {
                item.setEnabled(false)
                mDisposable.add(favoritePresenter.insertFavorite(country?.getName()!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ item.setEnabled(true) },
                                { throwable -> Log.e(TAG, "Unable to update username", throwable) }))
            }
            true
        } else if(id == R.id.menu_ci_add_friend) {
            val intent = Intent(this, FriendAddActivity::class.java)
            intent.putExtra("country", country)
            startActivity(intent)
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun starNotifyChange() {

    }
}
