package com.kwang0.hackinssa.data.models

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "tags",
        primaryKeys = ["friendId", "tagName"],
        foreignKeys = [ForeignKey(entity = Friend::class,
            parentColumns = arrayOf("friendId"),
            childColumns = arrayOf("friendId")
        )])
class Tag(@SerializedName("id")
          var friendId: String,
          @SerializedName("name")
          var tagName: String,
          @NonNull
          @SerializedName("created")
          @ColumnInfo(name = "tagCreated") var tagCreated: Long) : Serializable {}