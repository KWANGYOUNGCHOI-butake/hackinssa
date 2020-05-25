package com.kwang0.hackinssa.presentation.ui.activities.countryinfo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.squareup.picasso.Picasso
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.apache.commons.net.ntp.NTPUDPClient
import java.io.IOException
import java.net.InetAddress
import java.util.*

class CountryInfoActivity : BaseActivity() {
    val TAG = CountryInfoActivity::class.simpleName

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

        getIntentExtra()
        getNetworkTime()
    }

    fun getIntentExtra() {

        val country = intent?.extras?.getSerializable("country") as? Country

        country?.let {
            Picasso.get()
                    .load(CountryAdapter.BASE_IMG_URL_250_PX.toString() + it.getAlpha2Code()!!.toLowerCase() + ".png?raw=true")
                    .placeholder(R.drawable.ic_place_holder)
                    .into(iv)
            name_tv.text = it.getNativeName()
        }
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
            startActivity(intent)
            true
        } else super.onOptionsItemSelected(item)
    }
}
