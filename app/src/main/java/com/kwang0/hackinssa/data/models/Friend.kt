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
    @ColumnInfo(name = "friendId")
    var id: String? = null

    @SerializedName("avatar")
    @ColumnInfo(name = "friendAvatar")
    var avatar: String? = null

    @SerializedName("name")
    @ColumnInfo(name = "friendName")
    var name: String? = null

    @SerializedName("phone")
    @ColumnInfo(name = "friendPhone")
    var phone: String? = null

    @SerializedName("email")
    @ColumnInfo(name = "friendEmail")
    var email: String? = null

    @SerializedName("created")
    @ColumnInfo(name = "friendCreated")
    var created: Int? = null

    constructor(avatar: String?, name: String?, phone: String?, email: String?, created: Int?) {
        id = UUID.randomUUID().toString()
        this.avatar = avatar
        this.name = name
        this.phone = phone
        this.email = email
        this.created = created
    }

    constructor(@NonNull id: String?, avatar: String?, name: String?, phone: String?, email: String?, created: Int?) {
        this.id = id
        this.avatar = avatar
        this.name = name
        this.phone = phone
        this.email = email
        this.created = created
    }

}