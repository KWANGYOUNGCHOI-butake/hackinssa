package com.kwang0.hackinssa.data.dao.impl

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.TagDao
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable

class TagDaoImpl(private val context: Context): TagDao {
    private var database: InssaDatabase? = null
    private var tagDao: TagDao? = null

    init {
        database = InssaDatabase.getInstance(context)
        tagDao = database?.tagDao()
    }

    override fun getTag(): Flowable<Tag> {
        TODO("Not yet implemented")
    }

    override fun insertTag(tag: Tag): Completable {
        TODO("Not yet implemented")
    }

    override fun update(tag: Tag): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteTagById(friendId: Int): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteTagByName(tagName: String): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteTag(friendId: Int, tagName: String): Completable {
        TODO("Not yet implemented")
    }

}