package com.berkedursunoglu.mbnews.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.FragmentProfileDetailsPageBinding
import com.berkedursunoglu.mbnews.news.MainActivity


class ProfileDetailsPage : Fragment() {

    private lateinit var dataBinding:FragmentProfileDetailsPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_details_page,container,false)
        dataBinding.detailsPage = this
        return dataBinding.root
    }


    fun gotoUsernameEdit(){
        val action = ProfileDetailsPageDirections.actionProfileDetailsPageToProfileEditPage(1)
        view?.findNavController()?.navigate(action)
    }
    fun gotoPasswordEdit(){
        val action = ProfileDetailsPageDirections.actionProfileDetailsPageToProfileEditPage(2)
        view?.findNavController()?.navigate(action)
    }
    fun gotoEmailEdit(){
        val action = ProfileDetailsPageDirections.actionProfileDetailsPageToProfileEditPage(3)
        view?.findNavController()?.navigate(action)
    }

    fun gotoNewsAddPage(){
        val action = ProfileDetailsPageDirections.actionProfileDetailsPageToNewsAddPage()
        view?.findNavController()?.navigate(action)
    }

    private fun backPressed(){
        val backPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this@ProfileDetailsPage,backPressedCallback)
    }
}