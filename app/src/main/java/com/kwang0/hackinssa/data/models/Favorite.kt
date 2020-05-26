package com.kwang0.hackinssa.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorites")
class Favorite(@PrimaryKey
               @ColumnInfo(name = "favoriteName") var name: String) {

}