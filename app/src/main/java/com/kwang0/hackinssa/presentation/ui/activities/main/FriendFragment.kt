package com.kwang0.hackinssa.presentation.ui.activities.main

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment

import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.helper.IntentHelper
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.views.FriendView

class FriendFragment : Fragment() {

    lateinit var search_et: EditText

    private var friendView: FriendView? = null
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_friend, container, false)

        search_et = v.findViewById<EditText>(R.id.searchbar_et)

        friendViewSetUp(v)


        friendView?.friendPresenter?.search("")
//        search_et.textChanges()
////                .throttleLast(100, TimeUnit.MILLISECONDS)
//                .debounce(200, TimeUnit.MILLISECONDS)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
////                .filter({ chars ->
////                    val empty = TextUtils.isEmpty(chars.trim())
////                    !empty
////                })
//                .subscribe({ chars ->
//                    val searchTerm: String = chars.trim().toString()
//                    if (chars.trim().length == 0) {
//                        hideKeyboard()
//                        friendView?.friendPresenter?.clear()
//                    } else {
//                        friendView?.friendPresenter?.search(searchTerm)
//                    }
//                })
        
        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_friend, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_friend_add_friend) {
            IntentHelper.activityIntent(context, FriendAddActivity::class.java)
            true
        } else super.onOptionsItemSelected(item)
    }

    fun friendViewSetUp(v: View) {
        friendView = FriendView(v.context)
        friendView?.bindView(v)
    }
}
