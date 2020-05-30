package com.kwang0.hackinssa.presentation.ui.activities.countryinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.TransactionTooLargeException
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.presenters.CountryInfoPresenter
import com.kwang0.hackinssa.presentation.presenters.CountryInfoPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.CountryInfoPresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class CountryInfoActivity : BaseActivity(), CountryInfoPresenterView {
    val TAG = CountryInfoActivity::class.simpleName

    private var countryInfoPresenter: CountryInfoPresenter? = null

    private var menu: Menu? = null
    private var star_item: MenuItem? = null

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

        countryInfoPresenter = CountryInfoPresenterImpl(this, this)
        countryInfoPresenter?.onCreate()
    }

    override fun onStart() {
        super.onStart()
        menuInit()
    }

    override fun onStop() {
        super.onStop()
        countryInfoPresenter?.onStop()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_country_info, menu)
        this.menu = menu
        countryInfoPresenter?.onCreateStarMenu()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_ci_star) {
            this.star_item = item
            countryInfoPresenter?.onFavoriteChange()
            true
        } else if(id == R.id.menu_ci_add_friend) {
            countryInfoPresenter?.onFriendAddSelect()
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun notifyFavoriteChange(b: Boolean) {
        if(b) {
            menu?.getItem(0)?.setIcon(R.drawable.ic_star_fill)
        } else {
            menu?.getItem(0)?.setIcon(R.drawable.ic_star_border)
        }
    }

    override fun starBtnEnable() {
        star_item?.setEnabled(true)
    }

    override fun starBtnDisable() {
        star_item?.setEnabled(false)
    }

    override fun startFriendAddAct(country: Country?) {
        val intent = Intent(this, FriendAddActivity::class.java)
        intent.putExtra("country", country)
        startActivity(intent)
    }

    override fun setCountryFlag(code: String) {
        GlideHelper.loadImg(this, GlideHelper.countryFlag(code), iv)
    }

    override fun setNameText(name: String) {
        name_tv.text = name
    }

    override fun setTimeText(time: String) {
        time_tv.text = time
    }

    private fun menuInit() {
        invalidateOptionsMenu()
    }
}
