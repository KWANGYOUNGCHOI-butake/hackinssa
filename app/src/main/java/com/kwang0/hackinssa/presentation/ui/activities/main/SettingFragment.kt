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


class SettingFragment : Fragment() {

    lateinit var radioGrp: RadioGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_setting, container, false)

        radioGrp = v.findViewById<RadioGroup>(R.id.setting_radioGrp)

        if(App.localeHelper?.getLanguage().equals(LANGUAGE_KOREAN)) radioGrp.check(R.id.setting_radioBtn_ko)
        else radioGrp.check(R.id.setting_radioBtn_en)

        v.findViewById<RadioGroup>(R.id.setting_radioGrp).setOnCheckedChangeListener({ group, checkedId ->
            if(checkedId == R.id.setting_radioBtn_ko) setNewLocale(LANGUAGE_KOREAN, false)
            else if(checkedId == R.id.setting_radioBtn_en) setNewLocale(LANGUAGE_ENGLISH, false)
        })

        return v
    }


    private fun setNewLocale(language: String, restartProcess: Boolean): Boolean {
        App.localeHelper?.setNewLocale(context, language)
        IntentHelper.activityClearIntent(context, MainActivity::class.java)
        if (restartProcess) {
            System.exit(0)
        }
        return true
    }

}
