package com.colderz.project_bacu_app.common.binding_adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.databinding.BindingAdapter

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("touchListener")
fun View.setTouchListener(value: Boolean) {
    if (value) {
        this.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    val scaleDownX = ObjectAnimator.ofFloat(v, "scaleX", 0.92f)
                    val scaleDownY = ObjectAnimator.ofFloat(v, "scaleY", 0.92f)
                    scaleDownX.duration = 140
                    scaleDownY.duration = 140
                    val scaleDown = AnimatorSet()
                    scaleDown.play(scaleDownX).with(scaleDownY)
                    scaleDown.start()
                }
                MotionEvent.ACTION_UP -> {
                    val scaleUpX = ObjectAnimator.ofFloat(v, "scaleX", 1f)
                    val scaleUpY = ObjectAnimator.ofFloat(v, "scaleY", 1f)
                    scaleUpX.duration = 140
                    scaleUpY.duration = 140
                    val scaleUp = AnimatorSet()
                    scaleUp.play(scaleUpX).with(scaleUpY)
                    scaleUp.start()
                }
            }
            false
        }
    }

}