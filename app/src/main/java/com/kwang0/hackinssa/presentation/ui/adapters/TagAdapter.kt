package com.kwang0.hackinssa.presentation.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity
import com.kwang0.hackinssa.presentation.ui.extensions.TagMenuListener
import com.kwang0.hackinssa.presentation.ui.activities.taginfo.TagInfoActivity


class TagAdapter(var mContext: Context, var mData: MutableList<Tag>, var menuListener: TagMenuListener?)
    : RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    // for tagFragment menu click
    var chkArray = BooleanArray(0)

    fun addManyToList(tags: MutableList<Tag>) {
        this.mData = tags
        this.notifyDataSetChanged()
    }

    fun clearList() {
        with(this.mData) {
            this.clear()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapter.ViewHolder {
        val rootView: View
        rootView = LayoutInflater.from(mContext).inflate(R.layout.reuse_tag_rv_con, parent, false)
        return TagAdapter.ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: TagAdapter.ViewHolder, position: Int) {
        holder.tv.text = mData.get(position).tagName

        val isChk = this.menuListener?.menuChk ?: false

        if(isChk) {
            holder.chkBox.visibility = VISIBLE
            if(chkArray[position]) {
                holder.chkBox.isChecked = true
            } else {
                holder.chkBox.isChecked = false
            }
            holder.layout.setOnClickListener({ v ->
                chkArray[position] = if(chkArray[position]) false else true
                notifyItemChanged(position)
            })
            holder.layout.setOnLongClickListener{
                true
            }
        } else {
            holder.chkBox.visibility = GONE
            holder.layout.setOnClickListener({ v ->
                val intent = Intent(mContext, TagInfoActivity::class.java)
                intent.putExtra("tag", mData.get(position).tagName)
                mContext.startActivity(intent)
            })
            holder.layout.setOnLongClickListener{
                menuListener?.menuChanged()
                setChkArraySize()
                chkArray[position] = if(chkArray[position]) false else true
                true
            }
        }
    }
    fun setChkArraySize() {
        chkArray = BooleanArray(mData.size)
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
