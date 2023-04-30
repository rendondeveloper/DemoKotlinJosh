package com.asdeporte.asdeportev2.ui.inscription

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.aceinteract.android.stepper.StepperNavListener
import com.aceinteract.android.stepper.StepperNavigationView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.models.StepperSettings
import com.asdeporte.asdeportev2.data.models.StepperViewModel
import com.asdeporte.asdeportev2.databinding.ActivityInscriptionBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ng.softcom.android.utils.ui.findNavControllerFromFragmentContainer


@ExperimentalCoroutinesApi
class InscriptionActivity : AppCompatActivity(), StepperNavListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInscriptionBinding

    private val viewModel: StepperViewModel by lazy { ViewModelProvider(this)[StepperViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(
            findNavControllerFromFragmentContainer(R.id.frame_stepper),
            AppBarConfiguration.Builder(
                R.id.step_modality,
                R.id.step_kit,
                R.id.step_products,
                R.id.step_payment
            ).build()
        )

        binding.stepper.initializeStepper()
        binding.continueButton.setOnClickListener {
            binding.stepper.goToNextStep()
        }

        binding.resumeAction.setOnClickListener {
            startActivity(Intent(this, ResumeActivity::class.java))
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
        }
    }

    /*
    Stepper
     */
    private fun StepperNavigationView.initializeStepper() {
        viewModel.updateStepper(
            StepperSettings(
                widgetColor,
                textColor,
                textSize,
                iconSize
            )
        )

        stepperNavListener = this@InscriptionActivity
        setupWithNavController(findNavControllerFromFragmentContainer(R.id.frame_stepper))
    }
    override fun onStepChanged(step: Int) {
        //TODO("Not yet implemented")
    }
    override fun onCompleted() {
        //TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_inscription)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}