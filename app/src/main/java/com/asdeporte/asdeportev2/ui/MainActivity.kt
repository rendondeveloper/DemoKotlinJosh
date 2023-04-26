package com.asdeporte.asdeportev2.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ActivityMainBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.hermes.Hermes
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_tribu, R.id.navigation_more
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navController)
            this.invalidateOptionsMenu()
            return@setOnItemSelectedListener true
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    showNavigationBar()
                    showActionBar()
                }
                R.id.navigation_dashboard -> {
                    showNavigationBar()
                    showActionBar()
                }
                R.id.navigation_tribu -> {
                    showNavigationBar()
                    showActionBar()
                }
                R.id.navigation_more -> {
                    showNavigationBar()
                    showActionBar()
                }
                R.id.navigation_notifications -> {
                    hideNavigationBar()
                }
                R.id.createTribeFragment -> {
                    hideActionBar()
                }
                else -> {
                    hideNavigationBar()
                }
            }
        }

        supportActionBar?.elevation = 0F
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.orange_as_light)))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.asd_logo_large)
        binding.toolbar.title =  getString(R.string.settings_asdeporte)
        binding.toolbar.background = ColorDrawable(ContextCompat.getColor(this, R.color.orange_as_light))
        binding.toolbar.visibility = View.GONE
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
        setupInits()
    }

    private fun setupInits() {
        Hermes.Builder.apiKey = "pub_nLbIjlCWNKAdB_O3qgTaoQ"
        Hermes.Builder.baseUrl = "https://ms.asdeporte.com/v1/"
        Hermes.Builder.appVersion = Build.VERSION.RELEASE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_suscription -> {
                startActivity(Intent(this, SuscriptionActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                true
            }
            R.id.navigation_tribu -> {
                true
            }
            R.id.navigation_profile -> {
                navController.safelyNavigate(R.id.profileTribuAction)
                true
            }
            R.id.navigation_notifications -> {
                navController.safelyNavigate(R.id.toNotificationsAction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showActionBar() {
        supportActionBar?.show()
    }
    fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun showNavigationBar() {
        binding.navView.visibility = View.VISIBLE
    }
    private fun hideNavigationBar() {
        binding.navView.visibility = View.GONE
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val navigation = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment_activity_main)
            when(Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment_activity_main).currentDestination) {
                navigation.findDestination(R.id.navigation_home) -> {
                    showFinishDialog()
                }
                navigation.findDestination(R.id.navigation_notifications) -> {
                    showActionBar()
                }
                navigation.findDestination(R.id.navigation_new_post) -> {
                    showActionBar()
                }
                else -> {
                    showActionBar()
                }
            }
            Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment_activity_main).navigateUp()
        }
    }
    private fun showFinishDialog(){
        MaterialAlertDialogBuilder(this).apply {
            setTitle("are you sure?")
            setMessage("want to close the application ?")
            setPositiveButton("Yes") { _, _ -> finish() }
            setNegativeButton("No", null)
            show()
        }
    }

}