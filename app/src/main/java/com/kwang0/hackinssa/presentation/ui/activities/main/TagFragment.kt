package com.kwang0.hackinssa.presentation.ui.activities.main

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding4.widget.textChanges

import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_CREATED
import com.kwang0.hackinssa.helper.FlagHelper.FLAG_SORT_NAME
import com.kwang0.hackinssa.helper.hideKeyboard
import com.kwang0.hackinssa.presentation.ui.adapters.TagAdapter
import com.kwang0.hackinssa.presentation.ui.extensions.TagMenuListener
import com.kwang0.hackinssa.presentation.ui.views.TagView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class TagFragment : Fragment(), TagMenuListener {

    var menu: Menu? = null
    override var menuChk = false

    lateinit var search_bar: RelativeLayout
    lateinit var search_et: EditText
    lateinit var empty_tv: TextView

    private var tagView: TagView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_tag, container, false)

        search_bar = v.findViewById<RelativeLayout>(R.id.searchbar)
        search_et = v.findViewById<EditText>(R.id.searchbar_et)
        empty_tv = v.findViewById<TextView>(R.id.reuse_empty_tv)
        empty_tv.visibility = GONE

        tagViewSetUp(v)

        searchTextChanges(search_et)

        return v
    }

    fun searchTextChanges(et: EditText) {
        et.textChanges()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ chars ->
                    val searchTerm: String = chars.trim().toString()
                    if (chars.trim().length == 0) {
                        hideKeyboard()
                        tagView?.tagPresenter?.clear()
                    } else {
                        tagView?.tagPresenter?.searchByTagName(searchTerm)
                    }
                })
    }

    // 메뉴 생성 뷰페이저 변환시 호출 -> 메뉴 초기화
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tag, menu)
        this.menu = menu
        search_bar.visibility = VISIBLE

        super.onCreateOptionsMenu(menu, inflater)
    }

    // 메뉴선택
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return if (id == R.id.menu_tag_edit) {
            changeMenu()
            true
        } else if(id == R.id.menu_tag_delete) {
            tagView?.getmAdapter()?.chkSet?.toList()?.let {
                tagView?.tagPresenter?.deleteByTagNames(it)
            }
            true
        } else if(id == R.id.menu_tag_name_q) {
            tagView?.sortNameResults()
            true
        } else if(id == R.id.menu_tag_create_q) {
            tagView?.sortCreatedResults()
            true
        } else super.onOptionsItemSelected(item)
    }

    // TagAdapter 에서 item 을 길게 선택했을 때,
    // TagPresenter 의 deleteByTagNames 를 완료했을 때 메뉴를 변경해 줌
    override fun menuChanged() {
        changeMenu()
    }

    // menuChk 를 통해 현재 메뉴를 표시해 줌
    fun changeMenu() {
        if(menuChk) {
            hideTrashMenu()
        } else {
            showTrashMenu()
        }
        tagTrashSetInit()
    }

    // 뷰페이저 변환이 일어나면 menuChk값과 지울 태그들 set을 초기화 시켜 줌
    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if(menuVisible.not()) {
            menuChk = false
            tagTrashSetInit()
        }
    }

    // 지울 태그들 set을 초기화 시킴
    fun tagTrashSetInit() {
        tagView?.getmAdapter()?.chkSetInit()
        tagView?.getmAdapter()?.notifyDataSetChanged()
    }

    // 휴지통 메뉴를 표시해 줌
    fun showTrashMenu() {
        menuChk = true
        menu?.getItem(0)?.setVisible(false)
        menu?.getItem(1)?.setVisible(false)
        menu?.getItem(2)?.setVisible(false)
        menu?.getItem(3)?.setVisible(true)
        search_bar.visibility = GONE
    }

    // 휴지통 메뉴를 숨겨 줌
    fun hideTrashMenu() {
        menuChk = false
        menu?.getItem(0)?.setVisible(true)
        menu?.getItem(1)?.setVisible(true)
        menu?.getItem(2)?.setVisible(true)
        menu?.getItem(3)?.setVisible(false)
        search_bar.visibility = VISIBLE
    }

    fun tagViewSetUp(v: View) {
        tagView = TagView(v.context, this)
        tagView?.bindView(v)
    }
}
