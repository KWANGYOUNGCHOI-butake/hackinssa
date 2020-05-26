package com.kwang0.hackinssa.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "tags")
class Tag: Serializable {

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "tagId")
    var id: String

    @SerializedName("name")
    @ColumnInfo(name = "tagName")
    var name: String

    @SerializedName("created")
    @ColumnInfo(name = "tagCreated")
    var created: Int

    @Ignore
    constructor(name: String, created: Int) {
        id = UUID.randomUUID().toString()
        this.name = name
        this.created = created
    }

    constructor(@NonNull id: String, name: String, created: Int) {
        this.id = id
        this.name = name
        this.created = created
    }
}