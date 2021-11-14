package com.colderz.project_bacu_app.presentation.ui.custom_views

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.colderz.project_bacu_app.databinding.EmptyDotLayoutBinding

class CustomEmptyDot constructor(
    context: Context,
) : FrameLayout(context) {
    private val binding: EmptyDotLayoutBinding = EmptyDotLayoutBinding.inflate(
        LayoutInflater.from(context), this, true
    )
}