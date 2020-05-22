package com.kwang0.hackinssa.presentation.ui.activities.countryselect

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter.Companion.BASE_IMG_URL_250_PX
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.apache.commons.net.ntp.NTPUDPClient
import java.io.IOException
import java.net.InetAddress
import java.util.*


class CountrySelectActivity : BaseActivity() {
    val TAG = CountrySelectActivity::class.simpleName

    lateinit var time_tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_select)

        val country = intent.getSerializableExtra("country") as Country

        val iv = findViewById<ImageView>(R.id.cs_iv)
        val name_tv = findViewById<TextView>(R.id.cs_name_tv)
        time_tv = findViewById<TextView>(R.id.cs_time_tv)

        Picasso.get()
                .load(BASE_IMG_URL_250_PX.toString() + country.getAlpha2Code()!!.toLowerCase() + ".png?raw=true")
                .into(iv)

        name_tv.text = country.getNativeName()

        getNetworkTime()
    }

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

                    val time = Date(timeInfo.message.receiveTimeStamp.time)
                    time_tv.text = time.toString()
                }) { error -> Toast.makeText(this, "get Error!", Toast.LENGTH_SHORT).show() }
    }

    companion object {
        const val TIME_SERVER = "time-a.nist.gov"
    }
}
