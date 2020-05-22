package com.kwang0.hackinssa.presentation.ui.activities.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.kwang0.hackinssa.CountryModel
import com.kwang0.hackinssa.presentation.ui.views.CountryView
import com.kwang0.hackinssa.R

class CountryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_country, container, false)

        val search_et = v.findViewById<EditText>(R.id.searchbar_et)
        search_et.setHint(R.string.search_hint)

        val countryView = CountryView(context)
        countryView.bindView(v)
        countryView.recyclerInit()

        val mList: MutableList<CountryModel> = countryView.getmList() as MutableList<CountryModel>
        val mAdapter: CountryAdapter? = countryView.getmAdapter()

        mAdapter?.notifyDataSetChanged()

        return v
    }

}
