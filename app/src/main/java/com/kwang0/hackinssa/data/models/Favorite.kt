package com.kwang0.hackinssa.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorites")
class Favorite {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "fcId")
    var id: String? = null

    @ColumnInfo(name = "fcCountry")
    var country: Country? = null


    @Ignore
    constructor(country: Country) {
        id = UUID.randomUUID().toString()
        this.country = country
    }

    constructor(id: String, country: Country) {
        this.id = id
        this.country = country
    }
}