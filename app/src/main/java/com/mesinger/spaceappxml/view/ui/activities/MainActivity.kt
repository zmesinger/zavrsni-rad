package com.mesinger.spaceappxml.view.ui.activities

import android.R
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mesinger.spaceappxml.databinding.ActivityMainBinding
import com.mesinger.spaceappxml.view.ui.fragments.HomeFragment
import com.mesinger.spaceappxml.view.ui.fragments.PictureOfTheDayFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupNavController()
    }



    private fun setupNavController(){

        navHostFragment = supportFragmentManager.findFragmentById(com.mesinger.spaceappxml.R.id.fragmentContainerView) as NavHostFragment

        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController);
        setupNav()



    }



    private fun setupNav() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.mesinger.spaceappxml.R.id.homeFragment -> showBottomNav()
                com.mesinger.spaceappxml.R.id.addNewPhotoFragment -> showBottomNav()
                com.mesinger.spaceappxml.R.id.pictureOfTheDayFragment -> showBottomNav()
                com.mesinger.spaceappxml.R.id.libraryImageView -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {

        binding.bottomNavigationView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE

    }






}
