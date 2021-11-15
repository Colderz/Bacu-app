package com.colderz.project_bacu_app.presentation.ui.cards_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colderz.project_bacu_app.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(

) : ViewModel() {
    private val _navigateToAddGoalDialog = MutableLiveData<Event<Boolean>>()
    val navigateToAddGoalDialog: LiveData<Event<Boolean>>
        get() = _navigateToAddGoalDialog

    private val _changeToNextCategory = MutableLiveData<Event<Boolean>>()
    val changeToNextCategory: LiveData<Event<Boolean>>
        get() = _changeToNextCategory

    private val __changeToPreviousCategory = MutableLiveData<Event<Boolean>>()
    val changeToPreviousCategory: LiveData<Event<Boolean>>
        get() = __changeToPreviousCategory

    fun goToAddGoalDialog() {
        _navigateToAddGoalDialog.value = Event(true)
    }

    fun nextCategory() {
        _changeToNextCategory.value = Event(true)
    }

    fun previousCategory() {
        __changeToPreviousCategory.value = Event(true)
    }

}