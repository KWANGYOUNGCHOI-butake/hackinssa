package com.kwang0.hackinssa.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Tags(@SerializedName("tagList") var tagList: MutableList<Tag>): Serializable {
}