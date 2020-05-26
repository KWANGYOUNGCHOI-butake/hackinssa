package com.kwang0.hackinssa.presentation.ui.activities.taginfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity

class TagInfoActivity : BaseActivity() {
    var TAG = TagInfoActivity::class.simpleName

    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_info)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        getIntentExtra(intent)

    }

    fun getIntentExtra(intent: Intent?) {
        val tag = intent?.extras?.getString("tag")

        tag?.let {
            supportActionBar?.title = it
        }
    }
}
