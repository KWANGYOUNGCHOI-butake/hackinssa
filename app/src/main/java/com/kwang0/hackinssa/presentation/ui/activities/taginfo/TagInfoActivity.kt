package com.kwang0.hackinssa.presentation.ui.activities.taginfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.Chip
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.models.Tags
import com.kwang0.hackinssa.presentation.presenters.FriendInfoPresenter
import com.kwang0.hackinssa.presentation.presenters.TagPresenterView
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.views.FriendView

class TagInfoActivity : BaseActivity() {
    var TAG = TagInfoActivity::class.simpleName

    lateinit var toolbar: Toolbar

    private var friendView: FriendView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_info)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        friendViewSetUp()


        getIntentExtra(intent)

    }

    fun getIntentExtra(intent: Intent) {
        val tag = intent.extras?.getString("tag")

        tag?.let {
            friendView?.friendPresenter?.searchByTag(it)
            supportActionBar?.title = it
        }
    }

    fun friendViewSetUp() {
        friendView = FriendView(this)
        friendView?.bindView(this)
        friendView?.recyclerInit()
    }
}
