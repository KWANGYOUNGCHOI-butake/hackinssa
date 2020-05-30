package com.kwang0.hackinssa.presentation.presenters.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.data.dao.impl.FriendDaoImpl
import com.kwang0.hackinssa.data.dao.impl.TagDaoImpl
import com.kwang0.hackinssa.data.models.Country
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.models.Tags
import com.kwang0.hackinssa.data.repository.FriendRepository
import com.kwang0.hackinssa.data.repository.TagRepository
import com.kwang0.hackinssa.data.repository.impl.FriendRepositoryImpl
import com.kwang0.hackinssa.data.repository.impl.TagRepositoryImpl
import com.kwang0.hackinssa.helper.*
import com.kwang0.hackinssa.helper.exception.*
import com.kwang0.hackinssa.presentation.presenters.FriendAddPresenter
import com.kwang0.hackinssa.presentation.presenters.FriendAddPresenterView
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.presentation.ui.extensions.ChipAddListener
import com.kwang0.hackinssa.presentation.ui.views.ChipAddDialogView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java9.util.stream.Collector
import java9.util.stream.Collectors
import java9.util.stream.StreamSupport
import java.lang.Exception
import java.lang.NullPointerException
import java.util.*

class FriendAddPresenterImpl(private val context: Context, private var view: FriendAddPresenterView):
        FriendAddPresenter, ChipAddListener {
    private val TAG = FriendAddPresenterImpl::class.simpleName

    private var friendRepository: FriendRepository
    private var tagRepository: TagRepository
    private var friendAddSubscription: Disposable? = null

    private var country: Country? = null
    private var friend: Friend? = null
    private var tags: Tags? = null
    private var tagList: MutableList<Tag> = ArrayList<Tag>()

    private var avatarPath: String? = null
    private var countryPath: String? = null

    init {
        friendRepository = FriendRepositoryImpl(FriendDaoImpl(context))
        tagRepository = TagRepositoryImpl(TagDaoImpl(context))
    }

    override fun onCreate() {
        try {
            getIntentExtra((context as? Activity)?.intent)
        } catch (e: Exception) {
            Log.e(TAG, "Get Exception : " + e.message)
        }
    }

    override fun onStop() {
        if(friendAddSubscription?.isDisposed() ?: false)
            friendAddSubscription?.dispose()
    }

    override fun onAvatarSelect() {
        IntentHelper.galleryIntent((context as? Activity))
    }

    override fun onCountrySelect() {
        IntentHelper.activityResultIntent((context as? Activity), CountrySelectActivity::class.java, IntentHelper.COUNTRY_REQUEST_CODE)
    }

    override fun onChipAddSelect() {
        ChipAddDialogView(context, this).openPaymentDialog()
    }

    override fun onFriendAddSelect() {
        val friendName = view.getNameText()
        val friendPhone = view.getPhoneText()
        val friendEmail = view.getEmailText()

        view.addBtnDisable()

        val phoneutil = PhoneNumberUtil.getInstance()
        try {
            if(!TextUtils.isEmpty(friendName) &&
                    ((!TextUtils.isEmpty(friendPhone) && ValidHelper.isPhoneValid(friendPhone, countryPath)) ||
                            (!TextUtils.isEmpty(friendEmail) && ValidHelper.isEmailValid(friendEmail)))) {
                ValidHelper.isTagsValid(tagList)
                insertOrUpdateFriend(friend?.friendId,
                        avatarPath!!,
                        friendName,
                        phoneutil.format(phoneutil.parse(friendPhone, countryPath), PhoneNumberUtil.PhoneNumberFormat.NATIONAL),
                        friendEmail,
                        countryPath!!,
                        friend?.friendCreated ?: System.currentTimeMillis(),
                        tagList)
            } else {
                Toast.makeText(context, context.getString(R.string.friend_add_fail), Toast.LENGTH_LONG).show()
                view.addBtnEnable()
            }
        } catch (e: NullPointerException) {
            Toast.makeText(context, context.getString(R.string.exception_npe), Toast.LENGTH_LONG).show()
            view.addBtnEnable()
        } catch (e: PhoneException) {
            Toast.makeText(context, context.getString(R.string.exception_phone), Toast.LENGTH_LONG).show()
            view.addBtnEnable()
        } catch (e: EmailException) {
            Toast.makeText(context, context.getString(R.string.exception_email), Toast.LENGTH_LONG).show()
            view.addBtnEnable()
        } catch (e: TagException) {
            Toast.makeText(context, context.getString(R.string.exception_tag), Toast.LENGTH_LONG).show()
            view.addBtnEnable()
        } catch (e: TagSameException) {
            Toast.makeText(context, context.getString(R.string.tag_add_same_fail), Toast.LENGTH_LONG).show()
            view.addBtnEnable()
        } catch (e: TagSizeException) {
            Toast.makeText(context, context.getString(R.string.tag_add_fail), Toast.LENGTH_LONG).show()
            view.addBtnEnable()
        } catch (e: NumberParseException) {
            Toast.makeText(context, context.getString(R.string.exception_number), Toast.LENGTH_LONG).show()
            view.addBtnEnable()
        }
    }

    override fun imageRequestResult(data: String?) {
        avatarPath = data
        avatarPath?.let { view.setAvatar(it) }
    }

    override fun countryRequestResult(data: String?) {
        countryPath = data
        countryPath?.let { view.setCountryFlag(it) }
    }

    override fun onChipItemRemove(text:String) {
        tagList = view.removeChip(text, tagList)
    }

    private fun getIntentExtra(intent: Intent?) {
        country = intent?.extras?.getSerializable("country") as? Country
        friend = intent?.extras?.getSerializable("friend") as? Friend
        tags = intent?.extras?.getSerializable("tags") as? Tags

        friend?.let {
            avatarPath = it.friendAvatar
            countryPath = it.friendCountry

            view.setAvatar(it.friendAvatar)
            view.setNameText(it.friendName)
            view.setPhoneText(it.friendPhone)
            view.setEmailText(it.friendEmail)
            view.setCountryFlag(it.friendCountry)
        }

        country?.let {
            countryPath = it.getAlpha2Code()

            view.setCountryFlag(it.getAlpha2Code())
        }

        tags?.let {
            it.tagList.forEach({ tag -> onChipAddedFromExtra(tag) })
        }
    }

    private fun insertOrUpdateFriend(friendId: String?,
                                      friendAvatar: String,
                                      friendName: String,
                                      friendPhone: String?,
                                      friendEmail: String?,
                                      friendCountry: String,
                                      friendCreated: Long,
                                      tagList: MutableList<Tag>) {
        val friend = Friend(friendId = if(friendId == null) UUID.randomUUID().toString() else friendId,
                friendAvatar = friendAvatar,
                friendName = friendName,
                friendPhone = friendPhone,
                friendEmail = friendEmail,
                friendCountry = friendCountry,
                friendCreated = friendCreated)

        if(friendAddSubscription?.isDisposed() ?: false)
            friendAddSubscription?.dispose()
        if(friendId == null) {
            insertFriend(friend)
        } else {
            updateFriend(friend)
        }
    }

    private fun insertFriend(friend: Friend) {
        friendAddSubscription = friendRepository.insertFriend(friend)
                .andThen(tagRepository.deleteTagById(friend.friendId))
                .andThen(tagRepository.insertTags(
                        StreamSupport
                                .stream(tagList)
                                .map { tag -> Tag(friend.friendId, tag.tagName, tag.tagCreated) }
                                .collect(Collectors.toList())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.finishActivity()
                    view.addBtnEnable()
                }, { throwable -> view.handleError(throwable) })
    }

    private fun updateFriend(friend: Friend) {
        friendAddSubscription = friendRepository.updateFriend(friend)
                .andThen(tagRepository.deleteTagById(friend.friendId))
                .andThen(tagRepository.insertTags(
                        StreamSupport
                                .stream(tagList)
                                .map { tag -> Tag(friend.friendId, tag.tagName, tag.tagCreated) }
                                .collect(Collectors.toList())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.finishActivity()
                    view.addBtnEnable()
                }, { throwable -> view.handleError(throwable) })
    }

    override fun onChipAddedFromExtra(tag: Tag) {
        if(!canAddChip(tag.tagName)) return
        tagList.add(tag)
        view.addChipToChipGroup(tag.tagName)
    }

    override fun onChipAdded(chipStr: String) {
        if(!canAddChip(chipStr)) return
        tagList.add(Tag("", chipStr, System.currentTimeMillis()))
        view.addChipToChipGroup(chipStr)
    }

    fun canAddChip(tagName: String): Boolean {
        if(tagList.filter { it.tagName == tagName }.isNotEmpty()) {
            Toast.makeText(context, context.getString(R.string.tag_add_same_fail), Toast.LENGTH_LONG).show()
            return false
        } else if(tagList.size >= 5) {
            Toast.makeText(context, context.getString(R.string.exception_tag), Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}