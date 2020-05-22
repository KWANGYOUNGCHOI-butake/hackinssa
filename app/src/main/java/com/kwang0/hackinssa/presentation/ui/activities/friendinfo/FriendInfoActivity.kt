package com.kwang0.hackinssa.presentation.ui.activities.friendinfo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.chip.ChipGroup
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity

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

        phone_iv.setOnClickListener { v -> onClickPhone("tel:" + "1122334455") }
        email_iv.setOnClickListener { v -> onClickEmail("admin@gmail.com") }

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

    fun onClickEmail(recipient: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))

        try {
            startActivity(Intent.createChooser(intent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun onClickPhone(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        42)
            }
        } else {
            // Permission has already been granted
            callPhone(phoneNumber)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone("tel:" + "1122334455")
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    @SuppressLint("MissingPermission")
    fun callPhone(phoneNumber: String){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber))
        startActivity(intent)
    }
}
