package com.colderz.project_bacu_app.data.repository

import com.colderz.project_bacu_app.data.remote.CurrencyApi
import com.colderz.project_bacu_app.data.remote.dto.CurrenciesDto
import com.colderz.project_bacu_app.domain.repository.FinanceRepository
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val api: CurrencyApi
) : FinanceRepository {
    override suspend fun getCurrencies(accessKey: String): CurrenciesDto {
        return api.getCurrencies(accessKey)
    }
}