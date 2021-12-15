package com.colderz.project_bacu_app.presentation.ui.cards_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colderz.project_bacu_app.common.Event
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.use_case.GetAllGoalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getAllGoalsUseCase: GetAllGoalsUseCase
) : ViewModel() {

    private val _allGoalsFromDatabase = getAllGoalsUseCase()
    val allGoalFromDatabase: LiveData<List<FinanceGoalEntity>>
        get() = _allGoalsFromDatabase

    private val _navigateToAddGoalDialog = MutableLiveData<Event<Boolean>>()
    val navigateToAddGoalDialog: LiveData<Event<Boolean>>
        get() = _navigateToAddGoalDialog

    private val _changeToNextCategory = MutableLiveData<Event<Boolean>>()
    val changeToNextCategory: LiveData<Event<Boolean>>
        get() = _changeToNextCategory

    private val _changeToPreviousCategory = MutableLiveData<Event<Boolean>>()
    val changeToPreviousCategory: LiveData<Event<Boolean>>
        get() = _changeToPreviousCategory

    private val _navigateToHintDialog = MutableLiveData<Event<Boolean>>()
    val navigateToHintDialog: LiveData<Event<Boolean>>
        get() = _navigateToHintDialog

    fun goToAddGoalDialog() {
        _navigateToAddGoalDialog.value = Event(true)
    }

    fun nextCategory() {
        _changeToNextCategory.value = Event(true)
    }

    fun previousCategory() {
        _changeToPreviousCategory.value = Event(true)
    }

    fun goToHintDialog() {
        _navigateToHintDialog.value = Event(true)
    }

    fun prepareCorrectViewPagerData(
        goalCategory: String,
    ): List<FinanceGoalEntity>? {
        return _allGoalsFromDatabase.value?.filter { it.category == goalCategory }
    }
}