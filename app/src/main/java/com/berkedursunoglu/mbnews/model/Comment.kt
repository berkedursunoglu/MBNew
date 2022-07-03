package com.berkedursunoglu.mbnews.model

data class Comment(
    val commentId: Int,
    val content: String,
    val infoId: Int,
    val userName: String
)