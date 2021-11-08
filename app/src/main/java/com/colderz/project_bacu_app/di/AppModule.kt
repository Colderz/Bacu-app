package com.colderz.project_bacu_app.di

import com.colderz.project_bacu_app.common.Constants.BASE_URL
import com.colderz.project_bacu_app.data.remote.CurrencyApi
import com.colderz.project_bacu_app.data.repository.FinanceRepositoryImpl
import com.colderz.project_bacu_app.domain.repository.FinanceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrenciesApi(): CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)

    @Provides
    @Singleton
    fun provideCurrenciesRepository(api: CurrencyApi): FinanceRepository = FinanceRepositoryImpl(api)
}