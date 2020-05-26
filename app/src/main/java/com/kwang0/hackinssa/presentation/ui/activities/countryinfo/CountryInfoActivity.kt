package com.kwang0.hackinssa.presentation.ui.activities.countryinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.helper.PicassoHelper
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.squareup.picasso.Picasso
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.apache.commons.net.ntp.NTPUDPClient
import java.io.IOException
import java.net.InetAddress
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class CountryInfoActivity : BaseActivity() {
    val TAG = CountryInfoActivity::class.simpleName

    var country: Country? = null

    lateinit var toolbar: Toolbar
    lateinit var iv: ImageView
    lateinit var name_tv: TextView
    lateinit var time_tv: TextView

    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        iv = findViewById<ImageView>(R.id.ci_iv)
        name_tv = findViewById<TextView>(R.id.ci_name_tv)
        time_tv = findViewById<TextView>(R.id.ci_time_tv)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        country = intent?.extras?.getSerializable("country") as? Country

        country?.let {
            PicassoHelper.loadImg(CountryAdapter.BASE_IMG_URL_250_PX.toString() + it.getAlpha2Code()!!.toLowerCase() + ".png?raw=true", iv)
            name_tv.text = it.getNativeName()
            getLocaleTime(it.getTimezones()?.get(0))
        }
    }

    override fun onResume() {
        super.onResume()
//        getNetworkTime()
    }

    override fun onPause() {
        super.onPause()
//        compositeDisposable.dispose()
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

    // this is for get server time
    @SuppressLint("CheckResult")
    @Throws(IOException::class)
    fun getNetworkTime() {
        Single.just(true)
                .observeOn(Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val timeClient = NTPUDPClient()
                    val inetAddress = InetAddress.getByName(TIME_SERVER)
                    val timeInfo = timeClient.getTime(inetAddress)

                    getRealTime(timeInfo.message.receiveTimeStamp.time)
                }) { error -> Toast.makeText(this, "get Error!", Toast.LENGTH_SHORT).show() }
    }

    @SuppressLint("SetTextI18n")
    fun getRealTime(timeStamp: Long) {
        val disposable = Flowable.just(true)
                .observeOn(Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Boolean ->
                    kotlin.run {

                    }
                    while(true) {
                        val time = Date(timeStamp)
                        val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss (XXX)", Locale.US)
                        time_tv.text = sdf.format(time)
                    }
                }) { error: Throwable? -> time_tv.text = "error!!" }

        compositeDisposable.add(disposable)
    }

    companion object {
        const val TIME_SERVER = "time-a.nist.gov"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_country_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_ci_star) {
            true
        } else if(id == R.id.menu_ci_add_friend) {
            val intent = Intent(this, FriendAddActivity::class.java)
            intent.putExtra("country", country)
            startActivity(intent)
            true
        } else super.onOptionsItemSelected(item)
    }
}
