package com.kwang0.hackinssa.presentation.presenters

import com.kwang0.hackinssa.data.models.Tag

interface FriendAddContract {
    interface Presenter {
        fun onCreate()
        fun onStop()
        fun onAvatarSelect()
        fun onCountrySelect()
        fun onChipAddSelect()
        fun onFriendAddSelect()
        fun imageRequestResult(data: String?)
        fun countryRequestResult(data: String?)
        fun onChipItemRemove(text:String)
    }

    interface View {
        fun finishActivity()
        fun addChipToChipGroup(chipStr: String)
        fun removeChip(text:String, tagList: MutableList<Tag>): MutableList<Tag>
        fun addBtnEnable()
        fun addBtnDisable()
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
}