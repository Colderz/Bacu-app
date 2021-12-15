package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colderz.project_bacu_app.common.Event
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.use_case.AddNewGoalUseCase
import com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs.AddNewGoalDialogFragment.AddGoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewGoalViewModel @Inject constructor(
    private val addNewGoalUseCase: AddNewGoalUseCase
) : ViewModel() {
    private val _navigateToNextStep = MutableLiveData<Event<AddGoalState>>()
    val navigateToNextStep: LiveData<Event<AddGoalState>>
        get() = _navigateToNextStep

    private val _choiceIntervalButton = MutableLiveData<Int>()
    val choiceIntervalButton: LiveData<Int>
        get() = _choiceIntervalButton

    private val _openCalendarPickerDialog = MutableLiveData<Event<Boolean>>()
    val openCalendarPickerDialog: LiveData<Event<Boolean>>
        get() = _openCalendarPickerDialog

    var type: String = "actual"
    var category: String = ""
    var goalAmount: String = ""
    var balance: String = "0"
    var intervalChoice: String = ""
    var goalDate: String = ""
    var goalIntervalAmount: String = ""
    var goalName: String = ""

    fun nextStep(state: AddGoalState) {
        when (state) {
            AddGoalState.SET_AMOUNT -> {
                _navigateToNextStep.value = Event(AddGoalState.SET_INTERVAL)
            }
            AddGoalState.SET_INTERVAL -> {
                _navigateToNextStep.value = Event(AddGoalState.SET_DATE)
            }
            AddGoalState.SET_DATE -> {
                _navigateToNextStep.value = Event(AddGoalState.SET_NAME)
            }
            AddGoalState.SET_NAME -> {
                _navigateToNextStep.value = Event(AddGoalState.SET_AMOUNT_INTERVAL)
            }
            AddGoalState.SET_AMOUNT_INTERVAL -> {
                _navigateToNextStep.value = Event(AddGoalState.SET_GOAL)
            }
        }
    }

    fun saveGoal() {
        viewModelScope.launch(Dispatchers.IO) {
            addNewGoalUseCase(
                FinanceGoalEntity(
                    type,
                    category,
                    goalAmount,
                    balance,
                    intervalChoice,
                    goalDate,
                    goalIntervalAmount,
                    goalName
                )
            )
        }
    }

    fun setIntervalButton(choice: Int) {
        _choiceIntervalButton.value = choice
    }

    fun openDatePicker() {
        _openCalendarPickerDialog.value = Event(true)
    }
}