package com.asdeporte.asdeportev2.ui.inscription

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ActivityResumeBinding

class ResumeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResumeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.totalView.isTotal()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
    }
}