package com.kwang0.hackinssa.main;

import android.os.Bundle;
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout
import com.kwang0.hackinssa.R

class MainActivity: AppCompatActivity() {

    lateinit var container: ViewPager
    lateinit var tl: TabLayout

    fun mainActContentsInit() {
        container = findViewById<ViewPager>(R.id.main_container)
        tl = findViewById<TabLayout>(R.id.main_tl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActContentsInit()

        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        container.adapter = pagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl))
        tl.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

    }

    override fun onBackPressed() {
        if (container.getCurrentItem() == 0) {
            super.onBackPressed();
        }else {
            container.setCurrentItem(0);
        }
    }
}
