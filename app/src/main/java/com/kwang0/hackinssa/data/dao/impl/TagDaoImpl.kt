package com.kwang0.hackinssa.data.dao.impl

import android.content.Context
import com.kwang0.hackinssa.data.InssaDatabase
import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.dao.TagDao
import com.kwang0.hackinssa.data.models.Tag
import io.reactivex.Completable
import io.reactivex.Flowable

class TagDaoImpl(private val context: Context): TagDao {
    private var database: InssaDatabase? = null
    private var favoriteDao: FavoriteDao? = null

    init {
        setUpDatabase(context)
    }

    override fun getTag(): Flowable<Tag> {
        TODO("Not yet implemented")
    }

    override fun insertTag(tag: Tag): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteTag(tag: Tag) {
        TODO("Not yet implemented")
    }

    private fun setUpDatabase(context: Context) {
        database = InssaDatabase.getInstance(context)
        favoriteDao = database?.favoriteDao()
    }
}