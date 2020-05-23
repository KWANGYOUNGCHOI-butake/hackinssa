package com.kwang0.hackinssa.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend

class FriendAdapter(var mContext: Context?, var mData: MutableList<Friend?>?) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.ViewHolder {
        val rootView: View
        rootView = LayoutInflater.from(mContext).inflate(R.layout.reuse_tag_rv_con, parent, false)
        return FriendAdapter.ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    override fun onBindViewHolder(holder: FriendAdapter.ViewHolder, position: Int) {

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
}
