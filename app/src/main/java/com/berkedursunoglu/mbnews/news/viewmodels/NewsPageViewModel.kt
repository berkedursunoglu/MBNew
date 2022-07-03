package com.berkedursunoglu.mbnews.news.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.mbnews.API.RetrofitInstance
import com.berkedursunoglu.mbnews.Constants
import com.berkedursunoglu.mbnews.model.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsPageViewModel: ViewModel() {

    val fournews = MutableLiveData<ArrayList<NewsItem>>()
    val allnews = MutableLiveData<ArrayList<NewsItem>>()
    val lastTenNewsPublic = MutableLiveData<ArrayList<NewsItem>>()
    val lastTenNewsSport = MutableLiveData<ArrayList<NewsItem>>()
    val lastTenNewsEconomy = MutableLiveData<ArrayList<NewsItem>>()

    fun getFourNews(){
        viewModelScope.launch(Dispatchers.IO) {
             var array = RetrofitInstance.retrofitInstance.getNews()
            withContext(Dispatchers.Main){
                fournews.value = array
            }
        }
    }

    fun getAllNews(){
        viewModelScope.launch(Dispatchers.IO){
            var arrayall = RetrofitInstance.retrofitInstance.getAllNews()
            withContext(Dispatchers.Main){
                allnews.value = arrayall
            }
        }
    }

    fun getLasttenNewsbyCategoryId(categoryId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            var arrayten = RetrofitInstance.retrofitInstance.getLasttenNews(categoryId)
            withContext(Dispatchers.Main){
                if (categoryId == Constants.PUBLIC){
                    lastTenNewsPublic.value = arrayten
                }else if (categoryId == Constants.ECONOMY){
                    lastTenNewsEconomy.value = arrayten
                }else if (categoryId == Constants.SPORT){
                    lastTenNewsSport.value = arrayten
                }
            }
        }
    }
}