package com.kwang0.hackinssa.presentation.ui.adapters

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.App
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.helper.LocaleHelper
import com.kwang0.hackinssa.presentation.ui.activities.countryinfo.CountryInfoActivity
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.reuse_country_rv_con.*
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

            holder.bindView(item)
            setLayoutSelect(holder, item)
        } catch (e: IndexOutOfBoundsException) {
            Toast.makeText(mContext, mContext.getString(R.string.exception_out_of_bounds), Toast.LENGTH_LONG).show()
        }
    }
    private fun setLayoutSelect(holder: ViewHolder, item: Country) {
        holder.country_rv_layout.setOnClickListener {v ->
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindView(item: Country) {
            GlideHelper.loadImg(itemView.context, GlideHelper.countryFlag(item.getAlpha2Code()), country_rv_iv)
            country_rv_tv.text = if(App.localeHelper?.getLanguage() == LocaleHelper.LANGUAGE_KOREAN) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val countryRegion: Locale = Locale.Builder().setRegion(item.getAlpha2Code()).build()
                    val langKorean: Locale = Locale.Builder().setLanguage("ko").build()
                    item.setNativeName(countryRegion.getDisplayCountry(langKorean))
                }
                item.getNativeName()
            } else item.getName()
        }
    }

}