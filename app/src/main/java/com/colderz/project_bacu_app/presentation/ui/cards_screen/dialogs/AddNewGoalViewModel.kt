package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colderz.project_bacu_app.common.Event
import com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs.AddNewGoalDialogFragment.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNewGoalViewModel @Inject constructor(

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

    fun nextStep(state: AddGoalState) {
        when(state) {
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
                _navigateToNextStep.value = Event(AddGoalState.SET_DESCRIPTION)
            }
            AddGoalState.SET_DESCRIPTION -> {

            }
        }
    }

    fun setIntervalButton(choice: Int) {
        _choiceIntervalButton.value = choice
    }

    fun openDatePicker() {
        _openCalendarPickerDialog.value = Event(true)
    }
}