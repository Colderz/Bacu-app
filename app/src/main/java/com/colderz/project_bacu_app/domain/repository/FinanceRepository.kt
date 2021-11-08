package com.colderz.project_bacu_app.domain.repository

import com.colderz.project_bacu_app.data.remote.dto.CurrenciesDto

interface FinanceRepository {

    suspend fun getCurrencies(accessKey: String): CurrenciesDto
}