package com.colderz.project_bacu_app.presentation.ui.custom_views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.colderz.project_bacu_app.databinding.DashboardCategoryCardLayoutBinding

class DashboardCategoryCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    last: Boolean,
    title: String,
    drawable: Drawable? = null
) : ConstraintLayout(context, attrs) {
    private val binding: DashboardCategoryCardLayoutBinding =
        DashboardCategoryCardLayoutBinding.inflate(
            LayoutInflater.from(context), this, true
        )

    init {
        binding.isLast = last
        binding.categoryName = title
        binding.imageId = drawable
    }

    public fun selectCard(isSelected: Boolean) {
        binding.isSelected = isSelected
    }
}