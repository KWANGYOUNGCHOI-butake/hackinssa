package com.kwang0.hackinssa.presentation.ui.activities.taginfo

import android.content.Intent
import android.os.Bundle
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.views.FriendView
import kotlinx.android.synthetic.main.reuse_toolbar.*

class TagInfoActivity : BaseActivity() {
    var TAG = TagInfoActivity::class.simpleName

    private var friendView: FriendView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_info)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        friendViewSetUp()

        getIntentExtra(intent)

    }

    override fun onStart() {
        super.onStart()
        friendView?.friendPresenter?.restoreData()
    }

    override fun onPause() {
        super.onPause()
        friendView?.friendPresenter?.tearDown()
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
    }
}
