package com.kwang0.hackinssa.presentation.ui.activities.friendinfo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.size
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.helper.PicassoHelper
import com.kwang0.hackinssa.presentation.ui.activities.taginfo.TagInfoActivity
import com.squareup.picasso.Picasso

class FriendInfoActivity : BaseActivity() {

    lateinit var toolbar: Toolbar
    lateinit var avatar_iv: ImageView
    lateinit var name_tv: TextView
    lateinit var phone_tv: TextView
    lateinit var phone_iv: ImageView
    lateinit var email_tv: TextView
    lateinit var email_iv: ImageView
    lateinit var country_iv: ImageView
    lateinit var tag_cg: ChipGroup

    var friend: Friend? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_info)


        toolbar = findViewById<Toolbar>(R.id.toolbar)
        avatar_iv = findViewById<ImageView>(R.id.fi_avatar_iv)
        name_tv = findViewById<TextView>(R.id.fi_name_tv)
        phone_tv = findViewById<TextView>(R.id.fi_phone_tv)
        phone_iv = findViewById<ImageView>(R.id.fi_phone_iv)
        email_tv =findViewById<TextView>(R.id.fi_email_tv)
        email_iv = findViewById<ImageView>(R.id.fi_email_iv)
        country_iv = findViewById<ImageView>(R.id.fi_country_iv)
        tag_cg = findViewById<ChipGroup>(R.id.fi_tag_cg)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        getIntentExra(intent)

        phone_iv.setOnClickListener { v -> IntentHelper.phoneIntent(this, "010-1234-5678") }
        email_iv.setOnClickListener { v -> IntentHelper.emailIntent(this, "admin@gmail.com") }


        val chip = Chip(tag_cg.context)
        chip.text = "example"

        chip.isClickable = false
        chip.isCheckable = false
        chip.setOnClickListener({ v->
            val intent = Intent(this, TagInfoActivity::class.java)
            intent.putExtra("tag", chip.text.toString())
            startActivity(intent)
        })

        tag_cg.addView(chip)
    }

    fun getIntentExra(intent: Intent?) {
        friend = intent?.extras?.getSerializable("friend") as? Friend

        friend?.let {
            PicassoHelper.loadImg(it.friendAvatar, avatar_iv)
            name_tv.text = it.friendName
            phone_tv.text = it.friendPhone
            email_tv.text = it.friendEmail
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_friend_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_fi_edit) {
            val intent = Intent(this, FriendAddActivity::class.java)
            intent.putExtra("friend", friend)
            startActivity(intent)
            true
        } else super.onOptionsItemSelected(item)
    }
}
