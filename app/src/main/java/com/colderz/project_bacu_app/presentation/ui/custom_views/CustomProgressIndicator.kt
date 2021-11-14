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
    private val DEFAULT_AMOUNT_OF_CIRCLES = 5

    init {
        updateProgressView(1)
    }

    private fun drawCircles() {
        binding.circleContainer.removeAllViews()
        for (i in 1..DEFAULT_AMOUNT_OF_CIRCLES) {
            binding.circleContainer.addView(CustomEmptyDot(context))
        }
    }

    fun updateProgressView(step: Int) {
        drawCircles()
        binding.circleContainer.removeViewAt(step - 1)
        binding.circleContainer.addView(CustomFillDot(context), step - 1)
    }
}