package com.kwang0.hackinssa.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
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
import com.kwang0.hackinssa.App
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.FlagHelper
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_NAME
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.ui.activities.friendinfo.FriendInfoActivity
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.reuse_country_rv_con.*
import kotlinx.android.synthetic.main.reuse_friend_rv_con.*
import java.util.*

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

            holder.bindView(item)
            setLayoutSelect(holder, item)
            setPhoneSelect(holder, item)
            setEmailSelect(holder, item)
        } catch (e: IndexOutOfBoundsException) {
            Toast.makeText(mContext, mContext.getString(R.string.exception_out_of_bounds), Toast.LENGTH_LONG).show()
        }
    }

    private fun setLayoutSelect(holder: ViewHolder, item: Friend) {
        holder.friend_rv_layout.setOnClickListener { v ->
            if(mContext is MainActivity) {
                val intent = Intent(mContext, FriendInfoActivity::class.java)
                intent.putExtra("friend", item)
                mContext.startActivity(intent)
            }
        }
    }
    private fun setPhoneSelect(holder: ViewHolder, item: Friend) {
        holder.friend_rv_phone_iv.setOnClickListener { v -> IntentHelper.phoneIntent(mContext, item.friendPhone) }
    }
    private fun setEmailSelect(holder: ViewHolder, item: Friend) {
        holder.friend_rv_email_iv.setOnClickListener { v -> IntentHelper.emailIntent(mContext, item.friendEmail) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindView(item: Friend) {
            GlideHelper.loadImg(itemView.context, Uri.parse(item.friendAvatar), friend_rv_avatar_iv)
            friend_rv_name_tv.text = item.friendName

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
            friend_rv_contact_tv.text = contactStr
            if(TextUtils.isEmpty(item.friendPhone)) friend_rv_phone_iv.visibility = GONE else friend_rv_phone_iv.visibility = VISIBLE
            if(TextUtils.isEmpty(item.friendEmail)) friend_rv_email_iv.visibility = GONE else friend_rv_email_iv.visibility = VISIBLE
        }
    }
}
