package com.kwang0.hackinssa.presentation.ui.activities.main

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.adapters.TagAdapter
import com.kwang0.hackinssa.presentation.ui.extensions.TagMenuListener
import com.kwang0.hackinssa.presentation.ui.views.TagView

class TagFragment : Fragment(), TagMenuListener {

    var menu: Menu? = null
    override var menuChk = false

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


        tagView?.tagPresenter?.search("")

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tag, menu)
        this.menu = menu

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if(menuVisible.not()) {
            this.menuChk = false
            menuInit()
        }
    }

    fun menuInit() {
        tagView?.getmAdapter()?.setChkArraySize()
        tagView?.getmAdapter()?.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_tag_edit || id == R.id.menu_tag_delete) {
            changeMenu()
            true
        } else super.onOptionsItemSelected(item)
    }



    override fun menuChanged() {
        changeMenu()
    }

    fun changeMenu() {
        if(menuChk) {
            hideTrashMenu()
        } else {
            showTrashMenu()
        }
        menuInit()
    }

    fun showTrashMenu() {
        menuChk = true
        menu?.getItem(0)?.setVisible(false)
        menu?.getItem(1)?.setVisible(false)
        menu?.getItem(2)?.setVisible(false)
        menu?.getItem(3)?.setVisible(true)
    }
    fun hideTrashMenu() {
        menuChk = false
        menu?.getItem(0)?.setVisible(true)
        menu?.getItem(1)?.setVisible(true)
        menu?.getItem(2)?.setVisible(true)
        menu?.getItem(3)?.setVisible(false)
    }

    fun tagViewSetUp(v: View) {
        tagView = TagView(v.context, this)
        tagView?.bindView(v)
        tagView?.recyclerInit()
    }


}
