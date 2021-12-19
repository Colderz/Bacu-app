package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.colderz.project_bacu_app.common.Event
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.use_case.AddNewGoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditGoalViewModel @Inject constructor(
    private val addNewGoalUseCase: AddNewGoalUseCase
) : ViewModel() {
    private val _updateTemporaryBalance = MutableLiveData<Event<Int>>()
    val updateTemporaryBalance: LiveData<Event<Int>>
        get() = _updateTemporaryBalance

    private val _endOfEdit = MutableLiveData<Event<Boolean>>()
    val endOfEdit: LiveData<Event<Boolean>>
        get() = _endOfEdit

    fun updateTempBalance(amount: String) {
        _updateTemporaryBalance.value = Event(amount.toInt())
    }

    fun updateGoal(actualGoal: FinanceGoalEntity, actualBalance: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewGoalUseCase(
                FinanceGoalEntity(
                    actualGoal.type,
                    actualGoal.category,
                    actualGoal.amountGoal,
                    actualBalance,
                    actualGoal.interval,
                    actualGoal.deadline,
                    actualGoal.intervalAmount,
                    actualGoal.name
                )
            )
        }
        _endOfEdit.value = Event(true)
    }
}