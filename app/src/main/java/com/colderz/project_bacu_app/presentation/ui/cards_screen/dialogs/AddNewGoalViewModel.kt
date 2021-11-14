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

    fun nextStep(state: AddGoalState) {
        _navigateToNextStep.value = Event(state)
    }

    fun setIntervalButton(choice: Int) {
        _choiceIntervalButton.value = choice
    }
}