package com.kwang0.hackinssa.presentation.ui.activities.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.kwang0.hackinssa.R

class TagFragment : Fragment() {

    var menu: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_tag, container, false)
        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu
        menu.clear()
        inflater.inflate(R.menu.menu_tag, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_tag_edit) {
            menu?.getItem(0)?.setVisible(false)
            menu?.getItem(1)?.setVisible(false)
            menu?.getItem(2)?.setVisible(false)
            menu?.getItem(3)?.setVisible(true)
            true
        } else if (id == R.id.menu_tag_delete) {
            menu?.getItem(0)?.setVisible(true)
            menu?.getItem(1)?.setVisible(true)
            menu?.getItem(2)?.setVisible(true)
            menu?.getItem(3)?.setVisible(false)
            true
        } else super.onOptionsItemSelected(item)
    }
}
