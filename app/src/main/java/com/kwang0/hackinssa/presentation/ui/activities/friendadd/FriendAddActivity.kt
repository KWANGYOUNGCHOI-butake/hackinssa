package com.kwang0.hackinssa.presentation.ui.activities.friendadd

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.size
import com.google.android.material.chip.Chip
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.*
import com.kwang0.hackinssa.helper.IntentHelper.COUNTRY_REQUEST_CODE
import com.kwang0.hackinssa.helper.IntentHelper.IMG_REQUEST_CODE
import com.kwang0.hackinssa.helper.PermissionHelper.READ_STORAGE_REQUEST_CODE
import com.kwang0.hackinssa.presentation.presenters.FriendAddContract
import com.kwang0.hackinssa.presentation.presenters.impl.FriendAddPresenterImpl
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_friend_add.*
import kotlinx.android.synthetic.main.reuse_toolbar.*

class FriendAddActivity : BaseActivity(), FriendAddContract.View {
    private val TAG = FriendAddActivity::class.simpleName

    private var friendAddPresenter: FriendAddContract.Presenter? = null

    private var add_item: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_add)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        friendAddPresenter = FriendAddPresenterImpl(this, this)
        friendAddPresenter?.onCreate()

        fa_avatar_iv.setOnClickListener { v ->
            if(PermissionHelper.readStoragePermissionCheck(this))
                friendAddPresenter?.onAvatarSelect()
            else PermissionHelper.showReadStorageRequest(this)
        }
        fa_country_iv.setOnClickListener { v -> friendAddPresenter?.onCountrySelect() }
        fa_tag_add_iv.setOnClickListener { v -> friendAddPresenter?.onChipAddSelect() }
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            READ_STORAGE_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    friendAddPresenter?.onAvatarSelect()
                }
                return
            }
            else -> {
                // 다른 요청들 무시
            }
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
        val chip = Chip(fa_tag_cg.context)
        chip.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.textSmall))
        chip.textStartPadding = resources.getDimension(R.dimen.bigPadding)
        chip.textEndPadding = resources.getDimension(R.dimen.bigPadding)
        chip.text = chipStr

        chip.isClickable = false
        chip.isCheckable = false
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener { v ->
            friendAddPresenter?.onChipItemRemove(chip.text as String)
            fa_tag_cg.removeView(v)
        }
        fa_tag_cg.addView(chip, fa_tag_cg.size - 1)
    }

    override fun removeChip(text: String, tagList: MutableList<Tag>): MutableList<Tag> {
        return tagList.filter({ it.tagName != text }).toMutableList()
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
        Log.e(TAG, "Throwable : " + throwable?.message)
    }

    override fun getNameText(): String {
        return fa_name_et.editableText.toString().trim()
    }

    override fun getPhoneText(): String {
        return fa_phone_et.editableText.toString().trim()
    }

    override fun getEmailText(): String {
        return fa_email_et.editableText.toString().trim()
    }

    override fun setAvatar(path: String) {
        GlideHelper.loadImg(this, Uri.parse(path), fa_avatar_iv)
    }

    override fun setNameText(name: String) {
        fa_name_et.text = name.toEditable()
    }

    override fun setPhoneText(phone: String?) {
        fa_phone_et.text = phone?.toEditable()
    }

    override fun setEmailText(email: String?) {
        fa_email_et.text = email?.toEditable()
    }

    override fun setCountryFlag(code: String) {
        GlideHelper.loadImg(this, GlideHelper.countryFlag(code), fa_country_iv)
    }


}
