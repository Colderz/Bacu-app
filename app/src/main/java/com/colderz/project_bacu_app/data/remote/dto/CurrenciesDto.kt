package com.colderz.project_bacu_app.data.remote.dto

import com.colderz.project_bacu_app.domain.model.Currencies
import com.google.gson.annotations.SerializedName

data class CurrenciesDto(
    val success : Boolean?,
    val timestamp : Int?,
    val base : String?,
    val date : String?,
    @SerializedName("rates")
    val ratesDto : RatesDto?
)

fun CurrenciesDto.toCurrencies(): Currencies? {
      return ratesDto?.toRates()?.let {
          Currencies(
              timestamp = timestamp,
              date = date,
              rates = it
          )
      }
}
