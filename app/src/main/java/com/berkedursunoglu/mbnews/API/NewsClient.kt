package com.berkedursunoglu.mbnews.API

import com.berkedursunoglu.mbnews.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance {

    companion object{
        private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.baseUrl).build()
        val retrofitInstance: NewsAPI by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}