
package com.asdeporte.asdeportev2.ui.access

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)
        window.statusBarColor = Color.TRANSPARENT

        val navController = findNavController(R.id.nav_host_fragment_content_login)
        appBarConfiguration = AppBarConfiguration(navController.graph)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        return
    }

}