package com.asdeporte.asdeportev2.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

object AnimationsUtils{

    fun fadeOutAndGone(context: Context, view: View, duration: Long){
        val fadeOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        fadeOut.duration = duration
        fadeOut.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                view.visibility = View.GONE
            }
        })
        view.startAnimation(fadeOut)
    }

    fun fadeInAndVisible(context: Context, view: View, duration: Long){
        val fadeIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        fadeIn.duration = duration
        fadeIn.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                view.visibility = View.VISIBLE
            }
        })
        view.startAnimation(fadeIn)
    }

}