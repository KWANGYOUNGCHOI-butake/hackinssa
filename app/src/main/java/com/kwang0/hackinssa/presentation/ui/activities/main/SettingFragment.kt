package com.kwang0.hackinssa.presentation.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.kwang0.hackinssa.App
import com.kwang0.hackinssa.helper.LocaleHelper.Companion.LANGUAGE_ENGLISH
import com.kwang0.hackinssa.helper.LocaleHelper.Companion.LANGUAGE_KOREAN
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.helper.IntentHelper
import kotlinx.android.synthetic.main.fragment_setting.view.*


class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_setting, container, false)

        if(App.localeHelper?.getLanguage().equals(LANGUAGE_KOREAN)) v.setting_radioGrp.check(R.id.setting_radioBtn_ko)
        else v.setting_radioGrp.check(R.id.setting_radioBtn_en)

        v.findViewById<RadioGroup>(R.id.setting_radioGrp).setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.setting_radioBtn_ko) setNewLocale(LANGUAGE_KOREAN, false)
            else if(checkedId == R.id.setting_radioBtn_en) setNewLocale(LANGUAGE_ENGLISH, false)
        }

        return v
    }


    // 새로운 언어로 설정을 변경시켜 줌 ( 영어 - en , 한국 - ko )
    private fun setNewLocale(language: String, restartProcess: Boolean): Boolean {
        App.localeHelper?.setNewLocale(context, language)
        IntentHelper.activityClearIntent(context, MainActivity::class.java)
        if (restartProcess) {
            System.exit(0)
        }
        return true
    }

}
