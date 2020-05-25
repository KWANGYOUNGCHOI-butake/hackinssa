package com.kwang0.hackinssa.presentation.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.presentation.ui.extensions.TagMenuListener
import com.kwang0.hackinssa.presentation.ui.activities.taginfo.TagInfoActivity


class TagAdapter(var mContext: Context?, var mData: MutableList<Tag?>?, var menuListener: TagMenuListener?)
    : RecyclerView.Adapter<TagAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapter.ViewHolder {
        val rootView: View
        rootView = LayoutInflater.from(mContext).inflate(R.layout.reuse_tag_rv_con, parent, false)
        return TagAdapter.ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    override fun onBindViewHolder(holder: TagAdapter.ViewHolder, position: Int) {
        holder.tv.text = mData?.get(position)?.name

        holder.layout.setOnClickListener({ v ->
            val intent = Intent(mContext, TagInfoActivity::class.java)
            intent.putExtra("tag", mData?.get(position))
            mContext?.startActivity(intent)
        })
        holder.layout.setOnLongClickListener{
            menuListener?.menuChanged()
            true
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: RelativeLayout
        var tv: TextView
        var chkBox: CheckBox
        init {
            layout = itemView.findViewById<RelativeLayout>(R.id.tag_rv_layout)
            tv = itemView.findViewById<TextView>(R.id.tag_rv_tv)
            chkBox = itemView.findViewById<CheckBox>(R.id.tag_rv_chkBox)
        }
    }
}
