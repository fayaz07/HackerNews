package com.mohammadfayaz.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mohammadfayaz.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private lateinit var navHostFragment: NavHostFragment
  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    installSplashScreen()

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initFragmentHost()
  }

  private fun initFragmentHost() {
    navHostFragment =
      supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment

    navController = navHostFragment.navController

    setupActionBarWithNavController(navController)

    binding.bottomNavBar.setupWithNavController(navController)
  }

  override fun onBackPressed() {
    if (!navController.navigateUp())
      super.onBackPressed()
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp() || super.onSupportNavigateUp()
  }

}
