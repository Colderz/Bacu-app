package com.colderz.project_bacu_app.common.binding_adapters

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.colderz.project_bacu_app.R

@BindingAdapter(value = ["balance", "goal"], requireAll = true)
fun TextView.setGoalBalance(balance: String, goal: String) {
    val spannable = SpannableStringBuilder("$balance /$goal PLN")
    val indexOfSlash = balance.length
    spannable.setSpan(
        AbsoluteSizeSpan(12, true),
        indexOfSlash,
        spannable.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        indexOfSlash,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = spannable
}

@BindingAdapter("intervalAmount")
fun TextView.setIntervalAmount(intervalAmount: String) {
    this.text = "$intervalAmount zł"
}

@BindingAdapter("interval")
fun TextView.setInterval(interval: String) {
    when (interval) {
        "1" -> {
            this.text = context.getString(R.string.card_info_interval_day)
        }
        "2" -> {
            this.text = context.getString(R.string.card_info_interval_week)
        }
        "3" -> {
            this.text = context.getString(R.string.card_info_interval_month)
        }
    }
}