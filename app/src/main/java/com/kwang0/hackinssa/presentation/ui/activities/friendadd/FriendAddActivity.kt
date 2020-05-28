package com.kwang0.hackinssa.presentation.ui.activities.friendadd

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.size
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.models.Tags
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.helper.GlideHelper.getThumbnail
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.helper.IntentHelper.COUNTRY_REQUEST_CODE
import com.kwang0.hackinssa.helper.IntentHelper.IMG_REQUEST_CODE
import com.kwang0.hackinssa.helper.toEditable
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
import java.io.ByteArrayOutputStream
import java.io.File


class FriendAddActivity : BaseActivity(), FriendAddPresenterView, ChipAddListener {
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

    private var country: Country? = null
    private var friend: Friend? = null
    private var tagList: MutableList<Tag> = ArrayList<Tag>()

    private var avatarPath: String? = null
    private var countryPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_add)

        friendAddPresenter = FriendAddPresenterImpl(this, this)

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

        getIntentExtra(intent)

        avatar_iv.setOnClickListener({ v -> IntentHelper.galleryIntent(this) })
        country_iv.setOnClickListener({ v -> IntentHelper.activityResultIntent(this, CountrySelectActivity::class.java, COUNTRY_REQUEST_CODE) })
        tag_add_iv.setOnClickListener ({ v -> ChipAddDialogView(this, this).openPaymentDialog() })
    }

    fun getIntentExtra(intent: Intent?) {
        country = intent?.extras?.getSerializable("country") as? Country
        friend = intent?.extras?.getSerializable("friend") as? Friend
        val tags = intent?.extras?.getSerializable("tags") as? Tags

        friend?.let {
            avatarPath = it.friendAvatar
            countryPath = it.friendCountry

            GlideHelper.loadImg(this, Uri.parse(it.friendAvatar), avatar_iv)
            name_et.text = it.friendName.toEditable()
            phone_et.text = it.friendPhone?.toEditable()
            email_et.text = it.friendEmail?.toEditable()
            GlideHelper.loadImg(this, CountryAdapter.BASE_IMG_URL_250_PX.toString() + it.friendCountry.toLowerCase() + ".png?raw=true", country_iv)
        }

        country?.let {
            countryPath = it.getAlpha2Code()
            GlideHelper.loadImg(this, CountryAdapter.BASE_IMG_URL_250_PX.toString() + it.getAlpha2Code()!!.toLowerCase() + ".png?raw=true", country_iv)
        }

        tags?.let {
            it.tagList.forEach({ tag -> onChipAddedFromExtra(tag) })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {
            if(requestCode == IMG_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                avatarPath = data.data?.toString()
                System.out.println(avatarPath)
                GlideHelper.loadImg(this, Uri.parse(avatarPath), avatar_iv)
            }
            if(requestCode == COUNTRY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                countryPath = it.getStringExtra("country")
                GlideHelper.loadImg(this,CountryAdapter.BASE_IMG_URL_250_PX.toString() + countryPath?.toLowerCase() + ".png?raw=true", country_iv)
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
            add_item?.isEnabled = false

            if(avatarPath != null &&
                    !TextUtils.isEmpty(name_et.editableText.toString()) &&
                    (!TextUtils.isEmpty(phone_et.editableText.toString()) || !TextUtils.isEmpty(phone_et.editableText.toString())) &&
                    countryPath != null) {

                friendAddPresenter?.insertOrUpdateFriend(friend?.friendId,
                        avatarPath!!,
                        name_et.editableText.toString(),
                        phone_et.editableText.toString(),
                        email_et.editableText.toString(), countryPath!!, System.currentTimeMillis(),
                        tagList)
            } else {
                addBtnEnabled()
            }
            true
        } else super.onOptionsItemSelected(item)
    }

    fun onChipAddedFromExtra(tag: Tag) {
        if(tagList.filter { it.tagName == tag.tagName }.isNotEmpty() && tagList.size >= 5) return
        tagList.add(tag)
        val chip = Chip(tag_cg.context)
        chip.text = tag.tagName

        chip.isClickable = false
        chip.isCheckable = false
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener({ v ->
            tagList = StreamSupport.stream(tagList).filter({ it.tagName != chip.text }).collect(Collectors.toList())
            tag_cg.removeView(v)
        })

        tag_cg.addView(chip, tag_cg.size - 1)
    }

    override fun onChipAdded(chipStr: String) {
        if(tagList.filter { it.tagName == chipStr }.isNotEmpty() && tagList.size >= 5) return
        tagList.add(Tag("", chipStr, System.currentTimeMillis()))
        val chip = Chip(tag_cg.context)
        chip.text = chipStr

        chip.isClickable = false
        chip.isCheckable = false
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener({ v ->
            tagList = StreamSupport.stream(tagList).filter({ it.tagName != chip.text }).collect(Collectors.toList())
            tag_cg.removeView(v)
        })

        tag_cg.addView(chip, tag_cg.size - 1)
    }

    override fun finishActivity() {
        finish()
    }

    override fun addBtnEnabled() {
        add_item?.isEnabled = true
    }

    override fun handleError(throwable: Throwable?) {
        Log.d(TAG, "Throwable : " + throwable?.message)
    }

}
