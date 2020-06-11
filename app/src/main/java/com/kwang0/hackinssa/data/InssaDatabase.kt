package com.kwang0.hackinssa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kwang0.hackinssa.data.dao.FavoriteDao
import com.kwang0.hackinssa.data.dao.FriendDao
import com.kwang0.hackinssa.data.dao.TagDao
import com.kwang0.hackinssa.data.models.Favorite
import com.kwang0.hackinssa.data.models.Friend
import com.kwang0.hackinssa.data.models.Tag
import com.kwang0.hackinssa.helper.SingletonArgHolder
import com.kwang0.hackinssa.helper.SingletonHolder

// database singleton
@Database(entities = [Friend::class, Tag::class, Favorite::class], version = 1)
abstract class InssaDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun tagDao(): TagDao

    companion object : SingletonArgHolder<InssaDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, InssaDatabase::class.java, "Sample.db").build()
    })
}