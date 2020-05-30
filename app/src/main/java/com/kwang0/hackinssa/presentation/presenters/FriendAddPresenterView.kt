package com.kwang0.hackinssa.presentation.presenters

import android.view.View
import com.kwang0.hackinssa.data.models.Tag

interface FriendAddPresenterView {
    fun finishActivity()
    fun addChipToChipGroup(chipStr: String)
    fun removeChip(text:String, tagList: MutableList<Tag>): MutableList<Tag>
    fun addBtnEnable()
    fun addBtnDisable()
    fun showToast(text: String)
    fun handleError(throwable: Throwable?)
    fun getNameText(): String
    fun getPhoneText(): String
    fun getEmailText(): String
    fun setAvatar(path: String)
    fun setNameText(name: String)
    fun setPhoneText(phone: String?)
    fun setEmailText(email: String?)
    fun setCountryFlag(code: String)
}