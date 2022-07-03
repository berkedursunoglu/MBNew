package com.berkedursunoglu.mbnews.API

import com.berkedursunoglu.mbnews.model.Category
import com.berkedursunoglu.mbnews.model.News
import com.berkedursunoglu.mbnews.model.NewsItem
import com.berkedursunoglu.mbnews.model.Post
import retrofit2.http.*

interface NewsAPI {

        @GET("api/Info/GetLastFourNews")
        suspend fun getNews() : ArrayList<NewsItem>

        @GET("api/Info/GetInfos")
        suspend fun getAllNews(): ArrayList<NewsItem>

        @GET("/api/Info/GetByIdWithAllDetails/{infoId}")
        suspend fun getnewsDetail(@Path(value = "infoId",encoded= true) infoId:Int): NewsItem

        @POST("/api/Comment")
        suspend fun postComment(@Body post:Post)

        @GET("/api/Info/GetInfoWithCategoryLastTenNews/{categoryId}")
        suspend fun getLasttenNews(@Path(value = "categoryId", encoded = true) categoryId:Int) : ArrayList<NewsItem>

        @GET("/api/Category/{categoryId}")
        suspend fun getNewsbyCategory(@Path(value = "categoryId", encoded = true) categoryId:Int) : Category

}