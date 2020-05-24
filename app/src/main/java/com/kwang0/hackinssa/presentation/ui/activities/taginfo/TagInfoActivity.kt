package com.kwang0.hackinssa.presentation.ui.activities.taginfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.toEditable
import com.squareup.picasso.Picasso

class TagInfoActivity : AppCompatActivity() {
    var TAG = TagInfoActivity::class.simpleName

    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_info)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        getIntentExtra()
    }

    fun getIntentExtra() {
        val tag = intent?.extras?.getSerializable("tag") as? Tag

        tag?.let {
            supportActionBar?.title = it.name
        }

    }
}
