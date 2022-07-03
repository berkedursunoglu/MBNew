package com.berkedursunoglu.mbnews.news.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.mbnews.API.NewsAPI
import com.berkedursunoglu.mbnews.API.RetrofitInstance
import com.berkedursunoglu.mbnews.model.NewsItem
import com.berkedursunoglu.mbnews.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsPageDetailViewModel:ViewModel() {

    var newsDetail = MutableLiveData<NewsItem>()


    fun getNewsDetail(Id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            var newsArray = RetrofitInstance.retrofitInstance.getnewsDetail(Id)
            withContext(Dispatchers.Main){
                newsDetail.value = newsArray
            }
        }
    }

    fun commentPost(post: Post){
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.retrofitInstance.postComment(post)
        }
    }




}
