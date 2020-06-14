package com.kwang0.hackinssa.presentation.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_CREATED
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_NAME
import com.kwang0.hackinssa.helper.GlideHelper
import com.kwang0.hackinssa.presentation.ui.extensions.MenuListener
import com.kwang0.hackinssa.presentation.ui.activities.taginfo.TagInfoActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.reuse_friend_rv_con.*
import kotlinx.android.synthetic.main.reuse_tag_rv_con.*
import java.lang.NullPointerException


class TagAdapter(var mContext: Context, var mData: MutableList<Tag>, var menuListener: MenuListener?)
    : RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    var currentSort = FLAG_SORT_NAME
    var chkSet = HashSet<String>()

    fun addManyToList(tags: MutableList<Tag>) {
        this.mData = dataSort(tags)
        this.notifyDataSetChanged()
    }

    fun dataSort(tags: MutableList<Tag>): MutableList<Tag> {
        if(currentSort == FLAG_SORT_CREATED) {
            return tags.sortedBy { it.tagCreated }.toMutableList()
        } else  {
            return tags.sortedBy { it.tagName }.toMutableList()
        }
    }

    fun clearList() {
        with(this.mData) {
            this.clear()
        }
    }

    fun chkSetInit() {
        chkSet = HashSet<String>()
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
        try {
            val item: Tag = mData.get(position)
            val isChk = this.menuListener?.menuChk ?: false

            holder.bindView(item)
            if(isChk) {
                holder.bindViewIsChk(chkSet, item)
                setLayoutSelectIsChk(holder, item, position)
                setLayoutLongSelectIsChk(holder, item)
            } else {
                holder.bindViewIsUnChk()
                setLayoutSelectIsNotChk(holder, item)
                setLayoutLongSelectIsNotChk(holder, item)
            }
        } catch (e: NullPointerException) {
            Toast.makeText(mContext, mContext.getString(R.string.exception_npe), Toast.LENGTH_LONG).show()
        } catch (e: IndexOutOfBoundsException) {
            Toast.makeText(mContext, mContext.getString(R.string.exception_out_of_bounds), Toast.LENGTH_LONG).show()
        }
    }
    private fun setLayoutSelectIsChk(holder: ViewHolder, item: Tag, position: Int) {
        holder.tag_rv_layout.setOnClickListener { v ->
            if(!chkSet.contains(item.tagName)) chkSet.add(item.tagName)
            else chkSet.remove(item.tagName)
            chkSet.forEach { s -> println("Click : " + s) }
            notifyItemChanged(position)
        }
    }
    private fun setLayoutSelectIsNotChk(holder: ViewHolder, item: Tag) {
        holder.tag_rv_layout.setOnClickListener { v ->
            val intent = Intent(mContext, TagInfoActivity::class.java)
            intent.putExtra("tag", item.tagName)
            mContext.startActivity(intent)
        }
    }
    private fun setLayoutLongSelectIsChk(holder: ViewHolder, item: Tag) {

        holder.tag_rv_layout.setOnLongClickListener{
            true
        }
    }
    private fun setLayoutLongSelectIsNotChk(holder: ViewHolder, item: Tag) {
        holder.tag_rv_layout.setOnLongClickListener{
            menuListener?.menuChanged()
            chkSetInit()
            chkSet.add(item.tagName)
            chkSet.forEach { s -> println("Long Click : " + s) }
            true
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindView(item: Tag) {
            tag_rv_tv.text = item.tagName

        }
        fun bindViewIsChk(chkSet: HashSet<String>, item: Tag) {
            tag_rv_chkBox.visibility = VISIBLE
            tag_rv_chkBox.isChecked = chkSet.contains(item.tagName)
        }
        fun bindViewIsUnChk() {
            tag_rv_chkBox.visibility = GONE
        }
    }
}
