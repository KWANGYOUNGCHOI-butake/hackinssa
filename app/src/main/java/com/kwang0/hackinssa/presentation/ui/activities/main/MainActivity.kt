package com.kwang0.hackinssa.presentation.ui.activities.main;

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.activities.BaseActivity
import com.kwang0.hackinssa.presentation.ui.adapters.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_tab.*
import kotlinx.android.synthetic.main.reuse_toolbar.*


class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        val limit = if (pagerAdapter.getCount() > 1) pagerAdapter.getCount() - 1 else 1

        main_container.adapter = pagerAdapter
        main_container.offscreenPageLimit = limit

        main_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tl))
        main_tl.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(main_container))
    }

    // 기본페이지 이외의 페이지에서 back button 을 누르면 기본페이지로 이동하도록 만들어 줌
    override fun onBackPressed() {
        if (main_container.currentItem == 0) {
            super.onBackPressed()
        } else {
            main_container.currentItem = 0
        }
    }
}
