package com.kwang0.hackinssa.data.repository

import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable

interface TagRepository {
    fun getTag(): Flowable<Tag>
    fun insertOrUpdateTag(tag: Tag): Completable
    fun deleteTag(tag: Tag)
}