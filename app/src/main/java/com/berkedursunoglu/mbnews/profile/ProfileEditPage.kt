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
import androidx.navigation.fragment.navArgs
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.FragmentProfileEditPageBinding
import com.berkedursunoglu.mbnews.news.MainActivity


class ProfileEditPage : Fragment() {

    private lateinit var dataBinding: FragmentProfileEditPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBack()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile_edit_page, container, false)
        dataBinding.editPage = this
        val navargs:  ProfileEditPageArgs by navArgs()
        showLayout(navargs.whereToCame)
        return dataBinding.root
    }

    fun actionPage(){
        val action = ProfileEditPageDirections.actionProfileEditPageToProfileDetailsPage()
        view?.findNavController()?.navigate(action)
    }

    fun handleBack() {
        val backPressedCallback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               actionPage()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this,backPressedCallback)
    }

    private fun showLayout(whichLayout:Int){
        if (whichLayout == 1){
            dataBinding.changeUsernameLayout.visibility = View.VISIBLE
        }else if(whichLayout == 2){
            dataBinding.changePasswordLayout.visibility = View.VISIBLE
        }else if (whichLayout == 3){
            dataBinding.changeEmailLayout.visibility = View.VISIBLE
        }
    }
}