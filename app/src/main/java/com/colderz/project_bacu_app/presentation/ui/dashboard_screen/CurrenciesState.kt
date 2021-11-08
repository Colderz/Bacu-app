package com.colderz.project_bacu_app.presentation.ui.dashboard_screen

import com.colderz.project_bacu_app.domain.model.Currencies

data class CurrenciesState(
    val isLoading: Boolean = false,
    val currencies: Currencies? = null,
    val error: String = ""
)