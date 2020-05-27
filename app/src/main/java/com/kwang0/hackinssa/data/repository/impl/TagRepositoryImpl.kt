package com.kwang0.hackinssa.data.repository.impl

import com.kwang0.hackinssa.data.dao.TagDao
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.repository.TagRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class TagRepositoryImpl(private val tagDao: TagDao): TagRepository {
    override fun getTag(): Flowable<Tag> {
        return tagDao.getTag()
    }

    override fun insertOrUpdateTag(tag: Tag): Completable {
        return tagDao.insertTag(tag)
    }

    override fun deleteTag(tag: Tag): Completable {
        return tagDao.deleteTag(tag.friendId, tag.tagName)
    }
}