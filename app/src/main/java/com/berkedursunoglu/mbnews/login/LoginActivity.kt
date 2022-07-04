package com.berkedursunoglu.mbnews.login

import android.app.DirectAction
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.R.*
import com.berkedursunoglu.mbnews.databinding.ActivityLoginBinding
import com.berkedursunoglu.mbnews.databinding.ActivityProfileBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var dataBinding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layout.activity_login)
        bottomNavigation()
    }

    fun bottomNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        val navController = navHostFragment.navController

        dataBinding.bottomNavigationView.setupWithNavController(navController)

    }
}