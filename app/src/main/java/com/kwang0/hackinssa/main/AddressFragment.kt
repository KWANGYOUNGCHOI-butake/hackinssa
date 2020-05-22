package com.kwang0.hackinssa.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.kwang0.hackinssa.R

class AddressFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_address, container, false)

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_addr, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_addr_add_friend) {
            val intent = Intent(context, FriendAddActivity::class.java)
            startActivity(intent)
            true
        } else super.onOptionsItemSelected(item)
    }
}
