package com.kwang0.hackinssa.data.repository.impl

import com.kwang0.hackinssa.data.dao.TagDao
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.data.repository.TagRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow

class TagRepositoryImpl(private val tagDao: TagDao): TagRepository {
    override fun getTag(): Flowable<List<Tag>> {
        return tagDao.getTag()
    }

    override fun getTagById(friendId: String): Flowable<List<Tag>> {
        return tagDao.getTagById(friendId)
    }

    override fun insertTag(tag: Tag): Completable {
        return tagDao.insertTag(tag)
    }

    override fun deleteTagById(friendId: String): Completable {
        return tagDao.deleteTagById(friendId)
    }

    override fun deleteTag(friendId: String, tagName: String): Completable {
        return tagDao.deleteTag(friendId, tagName)
    }
}