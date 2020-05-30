package com.kwang0.hackinssa.presentation.ui.activities.friendadd

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.size
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.models.Tags
import com.kwang0.hackinssa.helper.*
import com.kwang0.hackinssa.helper.IntentHelper.COUNTRY_REQUEST_CODE
import com.kwang0.hackinssa.helper.IntentHelper.IMG_REQUEST_CODE
import com.kwang0.hackinssa.presentation.presenters.FriendAddPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendAddPresenterView
import com.kwang0.hackinssa.presentation.presenters.impl.FriendAddPresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.presentation.ui.extensions.ChipAddListener
import com.kwang0.hackinssa.presentation.ui.views.ChipAddDialogView
import java9.util.stream.Collectors
import java9.util.stream.StreamSupport


class FriendAddActivity : BaseActivity(), FriendAddPresenterView {
    private val TAG = FriendAddActivity::class.simpleName

    private var friendAddPresenter: FriendAddPresenter? = null

    lateinit var toolbar: Toolbar
    lateinit var avatar_iv: ImageView
    lateinit var name_et: EditText
    lateinit var phone_et: EditText
    lateinit var email_et: EditText
    lateinit var country_iv: ImageView
    lateinit var tag_cg: ChipGroup
    lateinit var tag_add_iv: ImageView

    private var add_item: MenuItem? = null

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
        tag_add_iv = findViewById<ImageView>(R.id.fa_tag_add_iv)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        friendAddPresenter = FriendAddPresenterImpl(this, this)
        friendAddPresenter?.onCreate()

        avatar_iv.setOnClickListener { v -> friendAddPresenter?.onAvatarSelect() }
        country_iv.setOnClickListener { v -> friendAddPresenter?.onCountrySelect() }
        tag_add_iv.setOnClickListener { v -> friendAddPresenter?.onChipAddSelect() }
    }

    override fun onStop() {
        super.onStop()
        friendAddPresenter?.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == IMG_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            friendAddPresenter?.imageRequestResult(data?.data?.toString())
        }
        if(requestCode == COUNTRY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            friendAddPresenter?.countryRequestResult(data?.getStringExtra("country"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_friend_add, menu)
        add_item = menu?.findItem(R.id.menu_fa_ok)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_fa_ok) {
            friendAddPresenter?.onFriendAddSelect()
            true
        } else super.onOptionsItemSelected(item)
    }


    override fun addChipToChipGroup(chipStr: String) {
        val chip = Chip(tag_cg.context)
        chip.text = chipStr

        chip.isClickable = false
        chip.isCheckable = false
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener { v ->
            friendAddPresenter?.onChipItemRemove(chip.text as String)
            tag_cg.removeView(v)
        }

        tag_cg.addView(chip, tag_cg.size - 1)
    }

    override fun removeChip(text: String, tagList: MutableList<Tag>): MutableList<Tag> {
        return StreamSupport.stream(tagList).filter({ it.tagName != text }).collect(Collectors.toList())
    }

    override fun finishActivity() {
        finish()
    }

    override fun addBtnEnable() {
        add_item?.isEnabled = true
    }

    override fun addBtnDisable() {
        add_item?.isEnabled = false
    }

    override fun handleError(throwable: Throwable?) {
        addBtnEnable()
        Log.d(TAG, "Throwable : " + throwable?.message)
    }

    override fun getNameText(): String {
        return name_et.editableText.toString().trim()
    }

    override fun getPhoneText(): String {
        return phone_et.editableText.toString().trim()
    }

    override fun getEmailText(): String {
        return email_et.editableText.toString().trim()
    }

    override fun setAvatar(path: String) {
        GlideHelper.loadImg(this, Uri.parse(path), avatar_iv)
    }

    override fun setNameText(name: String) {
        name_et.text = name.toEditable()
    }

    override fun setPhoneText(phone: String?) {
        phone_et.text = phone?.toEditable()
    }

    override fun setEmailText(email: String?) {
        email_et.text = email?.toEditable()
    }

    override fun setCountryFlag(code: String) {
        GlideHelper.loadImg(this, GlideHelper.countryFlag(code), country_iv)
    }


}
