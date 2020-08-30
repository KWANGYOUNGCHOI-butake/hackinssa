package com.kwang0.hackinssa.presentation.ui.activities.countryinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.presenters.CountryInfoContract
import com.kwang0.hackinssa.presentation.presenters.impl.CountryInfoPresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import kotlinx.android.synthetic.main.activity_country_info.*
import kotlinx.android.synthetic.main.reuse_toolbar.*

class CountryInfoActivity : BaseActivity(), CountryInfoContract.View {
    val TAG = CountryInfoActivity::class.simpleName

    private var countryInfoPresenter: CountryInfoContract.Presenter? = null

    private var menu: Menu? = null
    private var star_item: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

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
        countryInfoPresenter?.onStart()
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

    override fun handleError(throwable: Throwable?) {
        starBtnEnable()
        Log.e(TAG, "Throwable : " + throwable?.message)
    }

    override fun setCountryFlag(code: String) {
        GlideHelper.loadImg(this, GlideHelper.countryFlag(code), ci_iv)
    }

    override fun setNameText(name: String) {
        ci_name_tv.text = name
    }

    override fun setTimeText(time: String) {
        ci_time_tv.text = time
    }

    // 액티비티 start 시 메뉴를 초기화 시켜주기 위함
    private fun menuInit() {
        invalidateOptionsMenu()
    }
}
