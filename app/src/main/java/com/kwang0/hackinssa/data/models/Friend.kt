package com.kwang0.hackinssa.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "friends")
class Friend(@NonNull
             @SerializedName("friendId")
             @PrimaryKey
             @ColumnInfo(name = "friendId") val friendId: String,

             @NonNull
             @SerializedName("friendAvatar")
             @ColumnInfo(name = "friendAvatar") var friendAvatar: String,

             @NonNull
             @SerializedName("friendName")
             @ColumnInfo(name = "friendName") var friendName: String,

             @SerializedName("friendPhone")
             @ColumnInfo(name = "friendPhone") var friendPhone: String?,

             @SerializedName("friendEmail")
             @ColumnInfo(name = "friendEmail") var friendEmail: String?,

             @SerializedName("friendCountry")
             @ColumnInfo(name = "friendCountry") var friendCountry: String,

             @NonNull
             @SerializedName("friendCreated")
             @ColumnInfo(name = "friendCreated") var friendCreated: Long) : Serializable