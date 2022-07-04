package com.berkedursunoglu.mbnews.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.FragmentNewsAddPageBinding


class NewsAddPage : Fragment() {


    private lateinit var dataBinding:FragmentNewsAddPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news_add_page,container,false)
        dataBinding.newsAddPage = this
        return dataBinding.root
    }

    fun gotoProfileDetailsPage(){
        val action = NewsAddPageDirections.actionNewsAddPageToProfileDetailsPage()
        view?.findNavController()?.navigate(action)
    }


}