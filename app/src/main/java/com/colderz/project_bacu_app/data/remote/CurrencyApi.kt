package com.colderz.project_bacu_app.data.remote

import com.colderz.project_bacu_app.data.remote.dto.CurrenciesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/v1/latest")
    suspend fun getCurrencies(@Query(value = "access_key") accessKey: String): CurrenciesDto
}