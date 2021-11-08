package com.colderz.project_bacu_app.presentation.ui.dashboard_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colderz.project_bacu_app.common.Constants.ACCESS_KEY
import com.colderz.project_bacu_app.common.Resource
import com.colderz.project_bacu_app.domain.model.Currencies
import com.colderz.project_bacu_app.domain.use_case.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {

    //cached
    private val _currenciesFlow = MutableStateFlow<Resource<Currencies?>>(Resource.Loading(null))

    //public
    val currenciesFlow: StateFlow<Resource<Currencies?>> = _currenciesFlow

    init {
        getCurrencies()
    }

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrenciesUseCase(ACCESS_KEY).collect { _currenciesFlow.value = it }
        }
    }


    fun convert(rates: ArrayList<Double?>): ArrayList<Double?> {
        val pLNRate = rates[0]!!.toDouble()
        rates.removeAt(0)
        val currenciesFromPln = arrayListOf<Double?>()
        rates.forEach { currenciesFromPln.add(Math.round(it?.div(pLNRate)!! * 100.0) / 100.0) }
        return currenciesFromPln
    }
}
