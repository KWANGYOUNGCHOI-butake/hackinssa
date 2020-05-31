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


class MainActivity: BaseActivity() {

    lateinit var container: ViewPager
    lateinit var tl: TabLayout
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById<ViewPager>(R.id.main_container)
        tl = findViewById<TabLayout>(R.id.main_tl)
        toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        val limit = if (pagerAdapter.getCount() > 1) pagerAdapter.getCount() - 1 else 1

        container.adapter = pagerAdapter
        container.offscreenPageLimit = limit

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl))
        tl.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    // 기본페이지 이외의 페이지에서 back button 을 누르면 기본페이지로 이동하도록 만들어 줌
    override fun onBackPressed() {
        if (container.currentItem == 0) {
            super.onBackPressed()
        } else {
            container.currentItem = 0
        }
    }
}
