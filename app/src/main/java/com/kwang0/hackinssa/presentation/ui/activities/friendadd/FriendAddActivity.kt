package com.kwang0.hackinssa.presentation.ui.activities.friendadd

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.helper.IntentHelper.COUNTRY_REQUEST_CODE
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.helper.toEditable
import com.kwang0.hackinssa.helper.IntentHelper.IMG_REQUEST_CODE
import com.kwang0.hackinssa.helper.PicassoHelper
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.squareup.picasso.Picasso

class FriendAddActivity : BaseActivity() {

    lateinit var toolbar: Toolbar
    lateinit var avatar_iv: ImageView
    lateinit var name_et: EditText
    lateinit var phone_et: EditText
    lateinit var email_et: EditText
    lateinit var country_iv: ImageView
    lateinit var tag_cg: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_add)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        avatar_iv = findViewById<ImageView>(R.id.fa_avatar_iv)
        name_et = findViewById<EditText>(R.id.fa_name_et)
        phone_et = findViewById<EditText>(R.id.fa_phone_et)
        email_et = findViewById<EditText>(R.id.fa_email_et)
        country_iv = findViewById<ImageView>(R.id.fa_country_iv)
        tag_cg = findViewById<ChipGroup>(R.id.fa_tag_cg)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        getIntentExtra(intent)

        avatar_iv.setOnClickListener({ v -> IntentHelper.galleryIntent(this) })
        country_iv.setOnClickListener({ v -> IntentHelper.activityIntent(this, CountrySelectActivity::class.java) })
    }

    fun getIntentExtra(intent: Intent?) {
        val friend = intent?.extras?.getSerializable("friend") as? Friend
        val country = intent?.extras?.getSerializable("country") as? Country

        friend?.let {
            PicassoHelper.loadImg(it.avatar, avatar_iv)
            name_et.text = it.name?.toEditable()
            phone_et.text = it.phone?.toEditable()
            email_et.text = it.email?.toEditable()
        }

        country?.let {
            PicassoHelper.loadImg(CountryAdapter.BASE_IMG_URL_250_PX.toString() + it.getAlpha2Code()!!.toLowerCase() + ".png?raw=true", country_iv)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {
            if(requestCode == IMG_REQUEST_CODE && resultCode == Activity.RESULT_OK) PicassoHelper.loadImg(it.data, avatar_iv)
            if(requestCode == COUNTRY_REQUEST_CODE && resultCode == Activity.RESULT_OK) PicassoHelper.loadImg(it.data, country_iv)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_friend_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_fa_ok) {
            true
        } else super.onOptionsItemSelected(item)
    }

}
