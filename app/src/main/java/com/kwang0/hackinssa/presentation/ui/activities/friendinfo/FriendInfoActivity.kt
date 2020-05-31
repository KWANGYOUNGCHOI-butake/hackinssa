package com.kwang0.hackinssa.presentation.ui.activities.friendinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.models.Tags
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.presentation.presenters.FriendInfoPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendInfoPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.FriendInfoPresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.taginfo.TagInfoActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter

class FriendInfoActivity : BaseActivity(), FriendInfoPresenterView {
    private val TAG = FriendInfoActivity::class.simpleName

    private var friendInfoPresenter: FriendInfoPresenter? = null

    lateinit var toolbar: Toolbar
    lateinit var avatar_iv: ImageView
    lateinit var name_tv: TextView
    lateinit var phone_layout: RelativeLayout
    lateinit var phone_tv: TextView
    lateinit var phone_iv: ImageView
    lateinit var email_layout: RelativeLayout
    lateinit var email_tv: TextView
    lateinit var email_iv: ImageView
    lateinit var country_iv: ImageView
    lateinit var tag_cg: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_info)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        avatar_iv = findViewById<ImageView>(R.id.fi_avatar_iv)
        name_tv = findViewById<TextView>(R.id.fi_name_tv)
        phone_layout = findViewById<RelativeLayout>(R.id.fi_phone_layout)
        phone_tv = findViewById<TextView>(R.id.fi_phone_tv)
        phone_iv = findViewById<ImageView>(R.id.fi_phone_iv)
        email_layout = findViewById<RelativeLayout>(R.id.fi_email_layout)
        email_tv =findViewById<TextView>(R.id.fi_email_tv)
        email_iv = findViewById<ImageView>(R.id.fi_email_iv)
        country_iv = findViewById<ImageView>(R.id.fi_country_iv)
        tag_cg = findViewById<ChipGroup>(R.id.fi_tag_cg)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        friendInfoPresenter = FriendInfoPresenterImpl(this, this)
        friendInfoPresenter?.onCreate()

        phone_iv.setOnClickListener { v -> friendInfoPresenter?.onPhoneSelect() }
        email_iv.setOnClickListener { v -> friendInfoPresenter?.onEmailSelect() }

    }

    override fun onStart() {
        super.onStart()
        friendInfoPresenter?.onStart()
    }

    override fun onStop() {
        super.onStop()
        friendInfoPresenter?.onStop()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_friend_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_fi_edit) {
            friendInfoPresenter?.onEditSelect()
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun addFriendResult(friend: Friend) {
        setAvatar(friend.friendAvatar)
        setNameText(friend.friendName)
        setPhoneText(friend.friendPhone)
        setEmailText(friend.friendEmail)
        setCountryFlag(friend.friendCountry)
    }

    override fun addTagResultsToList(tagList: List<Tag>) {
        tagList.forEach { tag ->
            val chip = Chip(tag_cg.context)
            chip.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.textSmall))
            chip.textStartPadding = resources.getDimension(R.dimen.bigPadding)
            chip.textEndPadding = resources.getDimension(R.dimen.bigPadding)
            chip.text = tag.tagName

            chip.isClickable = false
            chip.isCheckable = false
            chip.isCloseIconVisible = false
            chip.setOnClickListener { v->
                val intent = Intent(this, TagInfoActivity::class.java)
                intent.putExtra("tag", chip.text.toString())
                startActivity(intent)
            }
            tag_cg.addView(chip)
        }
    }

    override fun clearTags() {
        tag_cg.removeAllViews()
    }

    override fun handleError(throwable: Throwable?) {
        Log.e(TAG, "Throwable : " + throwable?.message)
    }

    override fun setAvatar(path: String) {
        GlideHelper.loadImg(this, Uri.parse(path), avatar_iv)
    }

    override fun setNameText(name: String) {
        name_tv.text = name
    }

    override fun setPhoneText(phone: String?) {
        phone_tv.text = phone
        if(TextUtils.isEmpty(phone)) phone_layout.visibility = GONE else phone_layout.visibility = VISIBLE
    }

    override fun setEmailText(email: String?) {
        email_tv.text = email
        if(TextUtils.isEmpty(email)) email_layout.visibility = GONE else email_layout.visibility = VISIBLE
    }

    override fun setCountryFlag(code: String) {
        GlideHelper.loadImg(this, GlideHelper.countryFlag(code), country_iv)
    }
}
