package com.berkedursunoglu.mbnews.news.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.mbnews.API.RetrofitInstance
import com.berkedursunoglu.mbnews.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel: ViewModel() {

    val categoryNews = MutableLiveData<Category>()

    fun getNewsbyCategoryId(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val category = RetrofitInstance.retrofitInstance.getNewsbyCategory(id)
            withContext(Dispatchers.Main){
                categoryNews.value = category
            }
        }
    }




}