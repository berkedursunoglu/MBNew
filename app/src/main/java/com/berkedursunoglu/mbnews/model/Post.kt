package com.berkedursunoglu.mbnews.model

import com.google.gson.annotations.SerializedName

class Post(
    @SerializedName("userName")
    var userName:String,
    @SerializedName("content")
    var content:String,
    @SerializedName("infoId")
    var infoId:Int
) {
}