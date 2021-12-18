package com.colderz.project_bacu_app.common.binding_adapters

import androidx.databinding.BindingAdapter
import com.colderz.project_bacu_app.presentation.ui.custom_views.CustomProgressIndicator

@BindingAdapter("amountOfCircles")
fun CustomProgressIndicator.setAmountOfDots(amount: Int) {
    this.setAmountOfCircles(amount)
}
