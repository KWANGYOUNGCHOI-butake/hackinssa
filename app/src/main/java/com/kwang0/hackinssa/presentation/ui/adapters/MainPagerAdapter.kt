package com.kwang0.hackinssa.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kwang0.hackinssa.presentation.ui.activities.main.FriendFragment
import com.kwang0.hackinssa.presentation.ui.activities.main.CountryFragment
import com.kwang0.hackinssa.presentation.ui.activities.main.SettingFragment
import com.kwang0.hackinssa.presentation.ui.activities.main.TagFragment

class MainPagerAdapter(fm:FragmentManager): FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val PAGE_MAX_CNT = 4
    val countryFragment = CountryFragment()
    val tagFragment = TagFragment()
    val settingFragment = SettingFragment()
    val addressFragment = FriendFragment()

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }

    override fun getItem(position: Int): Fragment {
        val fragment = when(position)
        {
            1 -> countryFragment
            2 -> tagFragment
            3 -> settingFragment
            else -> addressFragment
        }
        fragment.setHasOptionsMenu(true)
        return fragment
    }
}