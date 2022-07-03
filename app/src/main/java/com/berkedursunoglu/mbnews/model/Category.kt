package com.berkedursunoglu.mbnews.model

data class Category(
    val categoryId: Int,
    val categoryName: String,
    val news: List<NewsItem>
)