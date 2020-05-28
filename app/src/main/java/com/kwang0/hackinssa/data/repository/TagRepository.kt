package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow

interface TagRepository {
    fun getTags(): Flowable<List<Tag>>
    fun getTagById(friendId: String): Flowable<List<Tag>>
    fun getTagByName(tagName: String): Flowable<List<Tag>>
    fun insertTag(tag: Tag): Completable
    fun insertTags(tagList: List<Tag>): Completable
    fun deleteTagById(friendId: String): Completable
    fun deleteTag(friendId: String, tagName: String): Completable
}