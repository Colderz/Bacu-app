package com.colderz.project_bacu_app.presentation.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import androidx.core.content.ContextCompat
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.databinding.DashboardCategorySectionLayoutBinding

class DashboardCategorySection @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : HorizontalScrollView(context, attrs) {
    private val binding: DashboardCategorySectionLayoutBinding =
        DashboardCategorySectionLayoutBinding.inflate(
            LayoutInflater.from(context), this, true
        )

    init {
        prepareSection()
    }

    private fun prepareSection() {
        val sectionCards: List<View> = listOf(
            DashboardCategoryCard(context, attrs = null, "Transport", true, ContextCompat.getDrawable(context, R.drawable.ic_transport)),
            DashboardCategoryCard(context, attrs = null, "Dom", false, ContextCompat.getDrawable(context, R.drawable.ic_home)),
            DashboardCategoryCard(context, attrs = null, "Podróże", false, ContextCompat.getDrawable(context, R.drawable.ic_journey)),
            DashboardCategoryCard(context, attrs = null, "Elektronika", false, ContextCompat.getDrawable(context, R.drawable.ic_electronics)),
            DashboardCategoryCard(context, attrs = null, "Prezenty", false, ContextCompat.getDrawable(context, R.drawable.ic_gift)),
            DashboardCategoryCard(context, attrs = null, "Hobby", false, ContextCompat.getDrawable(context, R.drawable.ic_hobby))
        )
        sectionCards.forEach { card -> configureSectionCard(card) }
    }

    private fun configureSectionCard(card: View) {
        binding.categorySectionContainer.addView(card)
    }

    enum class State {
        TRANSPORT,
        HOUSE,
        TRAVEL,
        ELECTRONIC,
        GIFTS,
        HOBBY
    }
}
