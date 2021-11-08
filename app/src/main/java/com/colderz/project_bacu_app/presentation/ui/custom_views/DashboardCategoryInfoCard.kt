package com.colderz.project_bacu_app.presentation.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.colderz.project_bacu_app.databinding.DashboardCategoryInfoCardLayoutBinding

class DashboardCategoryInfoCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private val binding: DashboardCategoryInfoCardLayoutBinding =
        DashboardCategoryInfoCardLayoutBinding.inflate(
            LayoutInflater.from(context), this, true
        )
}