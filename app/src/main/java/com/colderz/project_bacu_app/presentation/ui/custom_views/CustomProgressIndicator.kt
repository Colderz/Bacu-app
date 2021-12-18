package com.colderz.project_bacu_app.presentation.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.colderz.project_bacu_app.databinding.CustomProgressIndicatorLayoutBinding

class CustomProgressIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val binding: CustomProgressIndicatorLayoutBinding =
        CustomProgressIndicatorLayoutBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    private var amountOfCircles = 5

    init {
        updateProgressView(0)
    }

    private fun drawCircles() {
        binding.circleContainer.removeAllViews()
        for (i in 1..amountOfCircles) {
            binding.circleContainer.addView(CustomEmptyDot(context))
        }
    }

    fun setAmountOfCircles(amount: Int) {
        amountOfCircles = amount
        updateProgressView(0)
    }

    fun updateProgressView(step: Int) {
        drawCircles()
        binding.circleContainer.removeViewAt(step)
        binding.circleContainer.addView(CustomFillDot(context), step)
    }
}