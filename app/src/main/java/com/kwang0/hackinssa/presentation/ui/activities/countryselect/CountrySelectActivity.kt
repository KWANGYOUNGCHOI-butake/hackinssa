package com.kwang0.hackinssa.presentation.ui.activities.countryselect

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter.Companion.BASE_IMG_URL_250_PX
import com.squareup.picasso.Picasso
import org.apache.commons.net.ntp.NTPUDPClient
import java.net.InetAddress
import java.util.*


class CountrySelectActivity : BaseActivity() {
    val TAG = CountrySelectActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_select)

        val country = intent.getSerializableExtra("country") as Country

        val iv = findViewById<ImageView>(R.id.cs_iv)
        val name_tv = findViewById<TextView>(R.id.cs_name_tv)
        val time_tv = findViewById<TextView>(R.id.cs_time_tv)

        Picasso.get()
                .load(BASE_IMG_URL_250_PX.toString() + country.getAlpha2Code()!!.toLowerCase() + ".png?raw=true")
                .into(iv)

        name_tv.text = country.getName()


        val timeClient = NTPUDPClient()
        val inetAddress: InetAddress = InetAddress.getByName(TIME_SERVER)
        val timeInfo = timeClient.getTime(inetAddress)
        val returnTime = timeInfo.returnTime
        Log.d(TAG, "time : " + returnTime)
        val time = Date(returnTime)
        time_tv.text = time.time.toString()
    }

    companion object {
        const val TIME_SERVER = "time-a.nist.gov"
    }
}
