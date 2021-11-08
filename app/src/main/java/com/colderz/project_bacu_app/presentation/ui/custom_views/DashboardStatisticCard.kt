package com.colderz.project_bacu_app.presentation.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.colderz.project_bacu_app.databinding.DashboardStatisticCardLayoutBinding
import com.colderz.project_bacu_app.presentation.ui.dashboard_screen.DashboardViewModel

class DashboardStatisticCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs) {
    private val binding: DashboardStatisticCardLayoutBinding =
        DashboardStatisticCardLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private var state: State = State.FIRST

    init {
        initCardView()
        updateState(state)
    }

    private fun initCardView() {
        binding.textViewDay.setOnClickListener {
            updateState(State.FIRST)
        }
        binding.textViewWeek.setOnClickListener {
            updateState(State.SECOND)
        }
        binding.textViewMonth.setOnClickListener {
            updateState(State.THIRD)
        }
    }

    private fun updateState(state: State) {
        this.state = state
        binding.state = state
    }

    fun setViewModel(viewModel: DashboardViewModel) {
        binding.viewModel = viewModel
    }

    enum class State {
        FIRST, SECOND, THIRD
    }

}