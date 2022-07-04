package com.berkedursunoglu.mbnews.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.FragmentSignInPageBinding


class SignInPage : Fragment() {

    private lateinit var dataBinding:FragmentSignInPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_in_page,container,false)
        return dataBinding.root
    }


}