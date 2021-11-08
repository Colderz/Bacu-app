package com.colderz.project_bacu_app.domain.use_case

import com.colderz.project_bacu_app.common.Resource
import com.colderz.project_bacu_app.data.database.FinanceGoalDao
import com.colderz.project_bacu_app.data.remote.dto.toCurrencies
import com.colderz.project_bacu_app.domain.model.Currencies
import com.colderz.project_bacu_app.domain.repository.FinanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repository: FinanceRepository,
    private val financeGoalDao: FinanceGoalDao
) {
    operator fun invoke(accessKey: String): Flow<Resource<Currencies?>> = flow {
        try {
            emit(Resource.Loading<Currencies?>())
            val currencies: Currencies? = repository.getCurrencies(accessKey).toCurrencies()
            emit(Resource.Success(currencies))
        } catch (e: HttpException) {
            emit(Resource.Error<Currencies?>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Currencies?>("Couldn't reach server. Check your internet connection" + e.localizedMessage))
        }
    }
}