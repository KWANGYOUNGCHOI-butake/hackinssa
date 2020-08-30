package com.kwang0.hackinssa.presentation.ui.activities.friendinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.material.chip.Chip
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.presenters.FriendInfoContract
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.presenters.impl.FriendInfoPresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.taginfo.TagInfoActivity
import kotlinx.android.synthetic.main.activity_friend_info.*
import kotlinx.android.synthetic.main.reuse_toolbar.*

class FriendInfoActivity : BaseActivity(), FriendInfoContract.View {
    private val TAG = FriendInfoActivity::class.simpleName

    private var friendInfoPresenter: FriendInfoContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_info)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        friendInfoPresenter = FriendInfoPresenterImpl(this, this)
        friendInfoPresenter?.onCreate()

        fi_phone_iv.setOnClickListener { v -> friendInfoPresenter?.onPhoneSelect() }
        fi_email_iv.setOnClickListener { v -> friendInfoPresenter?.onEmailSelect() }

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
            val chip = Chip(fi_tag_cg.context)
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
            fi_tag_cg.addView(chip)
        }
    }

    override fun clearTags() {
        fi_tag_cg.removeAllViews()
    }

    override fun handleError(throwable: Throwable?) {
        Log.e(TAG, "Throwable : " + throwable?.message)
    }

    override fun setAvatar(path: String) {
        GlideHelper.loadImg(this, Uri.parse(path), fi_avatar_iv)
    }

    override fun setNameText(name: String) {
        fi_name_tv.text = name
    }

    override fun setPhoneText(phone: String?) {
        fi_phone_tv.text = phone
        if(TextUtils.isEmpty(phone)) fi_phone_layout.visibility = GONE else fi_phone_layout.visibility = VISIBLE
    }

    override fun setEmailText(email: String?) {
        fi_email_tv.text = email
        if(TextUtils.isEmpty(email)) fi_email_layout.visibility = GONE else fi_email_layout.visibility = VISIBLE
    }

    override fun setCountryFlag(code: String) {
        GlideHelper.loadImg(this, GlideHelper.countryFlag(code), fi_country_iv)
    }
}
