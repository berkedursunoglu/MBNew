package com.berkedursunoglu.mbnews.model

data class NewsItem(
    val category: Category,
    val categoryId: Int,
    val comments: List<Comment>,
    val content: String,
    val imageUrl: String,
    val infoId: Int,
    val newsName: String
)