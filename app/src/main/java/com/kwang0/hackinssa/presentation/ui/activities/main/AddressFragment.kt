package com.kwang0.hackinssa.presentation.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.adapters.FriendAdapter
import com.kwang0.hackinssa.presentation.ui.views.FriendView

class AddressFragment : Fragment() {

    lateinit var search_et: EditText
    lateinit var empty_tv: TextView

    private var friendView: FriendView? = null
    private var friendList: MutableList<Friend?>? = null
    private var friendAdapter: FriendAdapter? = null
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_address, container, false)
        
        friendView = FriendView(context)
        friendView?.bindView(v)
        friendView?.recyclerInit()

        friendList = friendView?.getmList()
        friendAdapter = friendView?.getmAdapter()

        friendList?.add(Friend("mina - avatar", "mina", "010-1234-5678", " sample@gmail.com"))
        friendAdapter?.notifyDataSetChanged()
        
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
