package com.kwang0.hackinssa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kwang0.hackinssa.data.models.Friend

@Database(entities = [Friend::class], version = 1)
abstract class FriendsDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao

    companion object {
        // 멀티 쓰레드 환경에서 발생하는 문제를 해결하기 위해
        // volatile 을 사용해서 변수의 read와 write를 Main Memory에서 진행
        @Volatile
        private var INSTANCE: FriendsDatabase? = null
        fun getInstance(context: Context): FriendsDatabase? {
            if (INSTANCE == null) {
                synchronized(FriendsDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                FriendsDatabase::class.java, "Sample.db")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}