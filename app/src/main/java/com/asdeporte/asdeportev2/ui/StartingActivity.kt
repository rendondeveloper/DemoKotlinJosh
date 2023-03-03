package com.asdeporte.asdeportev2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.utils.widget.MotionLabel
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ActivityMainBinding
import com.asdeporte.asdeportev2.databinding.ActivityStartingBinding
import com.asdeporte.asdeportev2.ui.onboarding.OnboardingActivity
import com.asdeporte.asdeportev2.utils.SharedPreferencesAsd

class StartingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartingBinding

    var openedNext = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

        binding = ActivityStartingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userid = SharedPreferencesAsd.getUserId(this) ?: ""
        println("getUserid: $userid")

        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                println("onTransitionCompleted")
                //startActivity(Intent(this@StartingActivity, MainActivity::class.java))
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                println("onTransitionChange p0: $p0")
                println("onTransitionChange p1: $p1")
                println("onTransitionChange p2: $p2")
                println("onTransitionChange p3: $p3")
                //0.9882739
                if (p3 > 0.83 && !openedNext) {
                    openedNext = true
                    if (userid.isNotEmpty()) {
                        startActivity(Intent(this@StartingActivity, MainActivity::class.java))
                    } else {
                        startActivity(Intent(this@StartingActivity, OnboardingActivity::class.java))
                    }
                }
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                println("onTransitionStarted")
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                println("onTransitionTrigger")
            }
        })

    }
}