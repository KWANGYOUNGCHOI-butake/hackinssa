package com.kwang0.hackinssa.presentation.ui.activities.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kwang0.hackinssa.App
import com.kwang0.hackinssa.LocaleManager.Companion.LANGUAGE_ENGLISH
import com.kwang0.hackinssa.LocaleManager.Companion.LANGUAGE_KOREAN
import com.kwang0.hackinssa.R


class SettingFragment : Fragment() {

    lateinit var radioGrp: RadioGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_setting, container, false)

        radioGrp = v.findViewById<RadioGroup>(R.id.setting_radioGrp)

        if(App.localeManager?.getLanguage().equals(LANGUAGE_KOREAN)) radioGrp.check(R.id.setting_radioBtn_ko)
        else radioGrp.check(R.id.setting_radioBtn_en)

        v.findViewById<RadioGroup>(R.id.setting_radioGrp).setOnCheckedChangeListener({ group, checkedId ->
            if(checkedId == R.id.setting_radioBtn_ko) setNewLocale(LANGUAGE_KOREAN, false)
            else if(checkedId == R.id.setting_radioBtn_en) setNewLocale(LANGUAGE_ENGLISH, false)
        })

        return v
    }


    private fun setNewLocale(language: String, restartProcess: Boolean): Boolean {
        App.localeManager?.setNewLocale(context, language)
        val i = Intent(context, MainActivity::class.java)
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        if (restartProcess) {
            System.exit(0)
        }
        return true
    }

}
