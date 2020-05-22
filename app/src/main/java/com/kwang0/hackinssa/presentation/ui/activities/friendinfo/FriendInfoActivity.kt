package com.kwang0.hackinssa.presentation.ui.activities.friendinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity

class FriendInfoActivity : BaseActivity() {

    lateinit var toolbar: Toolbar
    lateinit var avatar_iv: ImageView
    lateinit var name_tv: TextView
    lateinit var phone_tv: TextView
    lateinit var phone_iv: ImageView
    lateinit var email_tv: TextView
    lateinit var email_iv: ImageView
    lateinit var tag_cg: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_info)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_friend_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_fi_edit) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
