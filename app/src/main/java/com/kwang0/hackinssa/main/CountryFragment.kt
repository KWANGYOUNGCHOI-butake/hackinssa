package com.kwang0.hackinssa.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.kwang0.hackinssa.R

class CountryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_country, container, false)

        val search_et = v.findViewById<EditText>(R.id.search_bar_et)
        search_et.setHint(R.string.search_hint)

        return v
    }

}
