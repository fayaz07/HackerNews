package com.mohammadfayaz.hn.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mohammadfayaz.hn.R
import com.mohammadfayaz.hn.databinding.ActivityMainBinding
import com.mohammadfayaz.hn.utils.extensions.gone
import com.mohammadfayaz.hn.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    navController.addOnDestinationChangedListener { _, destination, _ ->
      if (destination.id == R.id.askStoryDetailFragment ||
        destination.id == R.id.commentsFragment
      ) {
        binding.bottomNavBar.gone()
      } else {
        binding.bottomNavBar.show()
      }
    }

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
