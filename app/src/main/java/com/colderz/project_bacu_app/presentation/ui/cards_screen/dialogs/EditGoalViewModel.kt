package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colderz.project_bacu_app.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditGoalViewModel @Inject constructor(

) : ViewModel() {
    private val _updateTemporaryBalance = MutableLiveData<Event<Int>>()
    val updateTemporaryBalance: LiveData<Event<Int>>
        get() = _updateTemporaryBalance

    fun updateTempBalance(amount: String) {
        _updateTemporaryBalance.value = Event(amount.toInt())
    }
}