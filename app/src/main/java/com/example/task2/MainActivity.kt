package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.fragContainer.id)
                as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupActionBar()
        setupDrawerLayout()
        setupBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp(binding.drawerLayout)

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupDrawerLayout() {
        binding.navBottom.setupWithNavController(navController)
        binding.navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, binding.drawerLayout)
    }

    private fun setupBackPressed() = onBackPressedDispatcher.addCallback {
        when {
            binding.drawerLayout.isDrawerOpen(binding.navigationView) -> binding.drawerLayout.close()
            navController.currentDestination?.id != navController.graph.startDestinationId -> {
                navController.popBackStack()
            }
            else -> finish()
        }
    }
}