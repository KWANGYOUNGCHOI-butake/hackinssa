package com.kwang0.hackinssa.presentation.ui.activities.main

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding4.widget.textChanges

import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.adapters.TagAdapter
import com.kwang0.hackinssa.presentation.ui.extensions.TagMenuListener
import com.kwang0.hackinssa.presentation.ui.views.FriendView
import com.kwang0.hackinssa.presentation.ui.views.TagView

class TagFragment : Fragment(), TagMenuListener {

    var menu: Menu? = null
    var trashChk = false

    lateinit var search_et: EditText
    lateinit var empty_tv: TextView

    private var tagView: TagView? = null
    private var tagList: MutableList<String?>? = null
    private var tagAdapter: TagAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_tag, container, false)

        search_et = v.findViewById<EditText>(R.id.searchbar_et)
        empty_tv = v.findViewById<TextView>(R.id.reuse_empty_tv)
        empty_tv.visibility = GONE

        tagViewSetUp(v)

        tagList = tagView?.getmList()
        tagAdapter = tagView?.getmAdapter()

        tagList?.add("tag1")
        tagAdapter?.notifyDataSetChanged()

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu
        menu.clear()
        inflater.inflate(R.menu.menu_tag, menu)

        if(trashChk) showTrashMenu()
        else hideTrashMenu()

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_tag_edit) {
            showTrashMenu()
            true
        } else if (id == R.id.menu_tag_delete) {
            hideTrashMenu()
            true
        } else super.onOptionsItemSelected(item)
    }

    fun showTrashMenu() {
        trashChk = true
        menu?.getItem(0)?.setVisible(false)
        menu?.getItem(1)?.setVisible(false)
        menu?.getItem(2)?.setVisible(false)
        menu?.getItem(3)?.setVisible(true)
    }
    fun hideTrashMenu() {
        trashChk = false
        menu?.getItem(0)?.setVisible(true)
        menu?.getItem(1)?.setVisible(true)
        menu?.getItem(2)?.setVisible(true)
        menu?.getItem(3)?.setVisible(false)
    }


    override fun menuChanged() {
        menu?.getItem(3)?.let {
            if(!it.isVisible) showTrashMenu()
        }
    }

    fun tagViewSetUp(v: View) {
        tagView = TagView(v.context, this)
        tagView?.bindView(v)
        tagView?.recyclerInit()
    }
}
