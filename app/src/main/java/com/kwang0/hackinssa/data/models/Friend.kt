package com.kwang0.hackinssa.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "friends")
class Friend: Serializable {

    @SerializedName("id")
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "friendid")
    var id: String? = null

    @SerializedName("avatar")
    @ColumnInfo(name = "friendavatar")
    var avatar: String? = null

    @SerializedName("name")
    @ColumnInfo(name = "friendname")
    var name: String? = null

    @SerializedName("phone")
    @ColumnInfo(name = "friendphone")
    var phone: String? = null

    @SerializedName("email")
    @ColumnInfo(name = "friendemail")
    var email: String? = null

    @SerializedName("tags")
    @ColumnInfo(name = "friendtags")
    var tag: List<String?>? = null

    constructor(avatar: String?, name: String?, phone: String?, email: String?, tag: List<String?>?) {
        id = UUID.randomUUID().toString()
        this.avatar = avatar
        this.name = name
        this.phone = phone
        this.email = email
        this.tag = tag
    }

    constructor(@NonNull id: String?, avatar: String?, name: String?, phone: String?, email: String?, tag: List<String?>?) {
        this.id = id
        this.avatar = avatar
        this.name = name
        this.phone = phone
        this.email = email
        this.tag = tag
    }

}