package com.kwang0.hackinssa.presentation.ui.adapters

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.App
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.helper.LocaleHelper
import com.kwang0.hackinssa.presentation.ui.activities.countryinfo.CountryInfoActivity
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity
import java.util.*


class CountryAdapter(val mContext: Context, var mData: MutableList<Country>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    fun addManyToList(countries: MutableList<Country>) {
        this.mData = countries
        this.notifyDataSetChanged()
    }

    fun clearList() {
        with(this.mData) {
            this.clear()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView: View
        rootView = LayoutInflater.from(mContext).inflate(R.layout.reuse_country_rv_con, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item: Country = mData.get(position)

            setCountryFlag(holder, item.getAlpha2Code())
            setCountryText(holder, item)
            setLayoutSelect(holder, item)
        } catch (e: IndexOutOfBoundsException) {
            Toast.makeText(mContext, mContext.getString(R.string.exception_out_of_bounds), Toast.LENGTH_LONG).show()
        }
    }

    private fun setCountryFlag(holder: ViewHolder, code: String) {
        GlideHelper.loadImg(mContext, GlideHelper.countryFlag(code), holder.iv)
    }
    private fun setCountryText(holder: ViewHolder, item: Country) {
        holder.tv.text = if(App.localeHelper?.getLanguage() == LocaleHelper.LANGUAGE_KOREAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val countryRegion: Locale = Locale.Builder().setRegion(item.getAlpha2Code()).build()
                val langKorean: Locale = Locale.Builder().setLanguage("ko").build()
                item.setNativeName(countryRegion.getDisplayCountry(langKorean))
            }
            item.getNativeName()
        } else item.getName()
    }
    private fun setLayoutSelect(holder: ViewHolder, item: Country) {
        holder.layout.setOnClickListener {v ->
            if(mContext is MainActivity) {
                val intent = Intent(mContext, CountryInfoActivity::class.java)
                intent.putExtra("country", item)
                mContext.startActivity(intent)
            } else if(mContext is CountrySelectActivity) {
                val intent = Intent();
                intent.putExtra("country", item.getAlpha2Code());
                mContext.setResult(RESULT_OK, intent);
                mContext.finish();
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: RelativeLayout
        var iv: ImageView
        var tv: TextView
        init {
            layout = itemView.findViewById<RelativeLayout>(R.id.country_rv_layout)
            iv = itemView.findViewById<ImageView>(R.id.country_rv_iv)
            tv = itemView.findViewById<TextView>(R.id.country_rv_tv)
        }
    }

    companion object {
        const val BASE_IMG_URL_250_PX = "https://github.com/hjnilsson/country-flags/blob/master/png250px/"
    }
}