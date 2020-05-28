package com.kwang0.hackinssa.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.ui.activities.friendinfo.FriendInfoActivity
import com.kwang0.hackinssa.helper.IntentHelper

class FriendAdapter(var mContext: Context, var mData: MutableList<Friend>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    fun addManyToList(friends: MutableList<Friend>) {
        this.mData = friends
        this.notifyDataSetChanged()
    }

    fun clearList() {
        with(this.mData) {
            this.clear()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.ViewHolder {
        val rootView: View
        rootView = LayoutInflater.from(mContext).inflate(R.layout.reuse_friend_rv_con, parent, false)
        return FriendAdapter.ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FriendAdapter.ViewHolder, position: Int) {
        val item: Friend? = mData.get(position)

        GlideHelper.loadImg(mContext, Uri.parse(item?.friendAvatar), holder.avatar_iv)
        holder.name_tv.text = item?.friendName
        holder.contact_tv.text = item?.friendPhone + "   " + item?.friendEmail

        holder.layout.setOnClickListener({ v ->
            val intent = Intent(mContext, FriendInfoActivity::class.java)
            intent.putExtra("friend", item)
            mContext.startActivity(intent)
        })
        holder.phone_iv.setOnClickListener { v -> IntentHelper.phoneIntent(mContext, item?.friendPhone) }
        holder.email_iv.setOnClickListener { v -> IntentHelper.emailIntent(mContext, item?.friendEmail) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: RelativeLayout
        var avatar_iv: ImageView
        var name_tv: TextView
        var contact_tv: TextView
        var phone_iv: ImageView
        var email_iv: ImageView
        init {
            layout = itemView.findViewById<RelativeLayout>(R.id.friend_rv_layout)
            avatar_iv = itemView.findViewById<ImageView>(R.id.friend_rv_avatar_iv)
            name_tv = itemView.findViewById<TextView>(R.id.friend_rv_name_tv)
            contact_tv = itemView.findViewById<TextView>(R.id.friend_rv_contact_tv)
            phone_iv = itemView.findViewById<ImageView>(R.id.friend_rv_phone_iv)
            email_iv = itemView.findViewById<ImageView>(R.id.friend_rv_email_iv)

        }
    }
}
