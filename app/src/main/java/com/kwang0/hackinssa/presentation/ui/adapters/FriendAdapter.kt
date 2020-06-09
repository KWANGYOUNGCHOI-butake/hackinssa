package com.kwang0.hackinssa.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.FlagHelper
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_NAME
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.ui.activities.friendinfo.FriendInfoActivity
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity
import java9.util.stream.Collectors
import java9.util.stream.Stream

class FriendAdapter(var mContext: Context, var mData: MutableList<Friend>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    var currentSort = FLAG_SORT_NAME

    fun addManyToList(friends: MutableList<Friend>) {
        this.mData = dataSort(friends)
        this.notifyDataSetChanged()
    }

    fun dataSort(friends: MutableList<Friend>): MutableList<Friend> {
        if(currentSort == FlagHelper.FLAG_SORT_CREATED) {
            return friends.sortedBy { it.friendCreated }.toMutableList()
        } else  {
            return friends.sortedBy { it.friendName }.toMutableList()
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
        try {
            val item: Friend = mData.get(position)

            setAvatarImage(holder, item)
            setNameText(holder, item)
            setContactText(holder, item)
            setLayoutSelect(holder, item)
            setPhoneSelect(holder, item)
            setEmailSelect(holder, item)
        } catch (e: IndexOutOfBoundsException) {
            Toast.makeText(mContext, mContext.getString(R.string.exception_out_of_bounds), Toast.LENGTH_LONG).show()
        }
    }

    private fun setAvatarImage(holder: ViewHolder, item: Friend) {
        GlideHelper.loadImg(mContext, Uri.parse(item.friendAvatar), holder.avatar_iv)
    }
    private fun setNameText(holder: ViewHolder, item: Friend) {
        holder.name_tv.text = item.friendName
    }
    private fun setContactText(holder: ViewHolder, item: Friend) {
        var contactStr = ""
        when {
            !TextUtils.isEmpty(item.friendPhone) && !TextUtils.isEmpty(item.friendEmail) ->
                contactStr = item.friendPhone + ", " + item.friendEmail ?: ""
            !TextUtils.isEmpty(item.friendPhone) ->
                contactStr = item.friendPhone ?: ""
            !TextUtils.isEmpty(item.friendEmail) ->
                contactStr = item.friendEmail ?: ""
            else -> contactStr = ""
        }
        holder.contact_tv.text = contactStr
        if(TextUtils.isEmpty(item.friendPhone)) holder.phone_iv.visibility = GONE else holder.phone_iv.visibility = VISIBLE
        if(TextUtils.isEmpty(item.friendEmail)) holder.email_iv.visibility = GONE else holder.email_iv.visibility = VISIBLE
    }
    private fun setLayoutSelect(holder: ViewHolder, item: Friend) {
        holder.layout.setOnClickListener { v ->
            if(mContext is MainActivity) {
                val intent = Intent(mContext, FriendInfoActivity::class.java)
                intent.putExtra("friend", item)
                mContext.startActivity(intent)
            }
        }
    }
    private fun setPhoneSelect(holder: ViewHolder, item: Friend) {
        holder.phone_iv.setOnClickListener { v -> IntentHelper.phoneIntent(mContext, item.friendPhone) }
    }
    private fun setEmailSelect(holder: ViewHolder, item: Friend) {
        holder.email_iv.setOnClickListener { v -> IntentHelper.emailIntent(mContext, item.friendEmail) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: ConstraintLayout
        var avatar_iv: ImageView
        var name_tv: TextView
        var contact_tv: TextView
        var phone_iv: ImageView
        var email_iv: ImageView
        init {
            layout = itemView.findViewById<ConstraintLayout>(R.id.friend_rv_layout)
            avatar_iv = itemView.findViewById<ImageView>(R.id.friend_rv_avatar_iv)
            name_tv = itemView.findViewById<TextView>(R.id.friend_rv_name_tv)
            contact_tv = itemView.findViewById<TextView>(R.id.friend_rv_contact_tv)
            phone_iv = itemView.findViewById<ImageView>(R.id.friend_rv_phone_iv)
            email_iv = itemView.findViewById<ImageView>(R.id.friend_rv_email_iv)

        }
    }
}
