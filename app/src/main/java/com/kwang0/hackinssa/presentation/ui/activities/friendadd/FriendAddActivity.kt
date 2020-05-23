package com.kwang0.hackinssa.presentation.ui.activities.friendadd

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.squareup.picasso.Picasso
import java.util.*

class FriendAddActivity : BaseActivity() {

    lateinit var toolbar: Toolbar
    lateinit var iv: ImageView
    lateinit var name_et: EditText
    lateinit var phone_et: EditText
    lateinit var email_et: EditText
    lateinit var country_iv: ImageView
    lateinit var tag_cg: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_add)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        iv = findViewById<ImageView>(R.id.fa_avatar_iv)
        name_et = findViewById<EditText>(R.id.fa_name_et)
        phone_et = findViewById<EditText>(R.id.fa_phone_et)
        email_et = findViewById<EditText>(R.id.fa_email_et)
        country_iv = findViewById<ImageView>(R.id.fa_country_iv)
        tag_cg = findViewById<ChipGroup>(R.id.fa_tag_cg)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        iv.setOnClickListener({ v ->
            val intent = Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(intent, "Select Image"), IMG_REQUEST_CODE)
        })
        country_iv.setOnClickListener({ v ->
            val intent = Intent(this, CountrySelectActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {
            if(requestCode == IMG_REQUEST_CODE && resultCode == Activity.RESULT_OK) Picasso.get().load(it.data).into(iv)
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

    companion object {
        const val IMG_REQUEST_CODE = 1000
    }
}
