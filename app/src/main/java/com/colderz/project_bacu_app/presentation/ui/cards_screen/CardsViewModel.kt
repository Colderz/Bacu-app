package com.colderz.project_bacu_app.presentation.ui.cards_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colderz.project_bacu_app.common.Event
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.use_case.AddNewGoalUseCase
import com.colderz.project_bacu_app.domain.use_case.GetAllGoalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    getAllGoalsUseCase: GetAllGoalsUseCase,
    private val addNewGoalUseCase: AddNewGoalUseCase
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

    private val _navigateToEditGoalDialog = MutableLiveData<Event<FinanceGoalEntity>>()
    val navigateToEditGoalDialog: LiveData<Event<FinanceGoalEntity>>
        get() = _navigateToEditGoalDialog

    private val _navigateToGoalSuccess = MutableLiveData<Event<Boolean>>()
    val navigateToGoalSuccess: LiveData<Event<Boolean>>
        get() = _navigateToGoalSuccess

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

    fun openEditGoalDialog(goal: FinanceGoalEntity) {
        _navigateToEditGoalDialog.value = Event(goal)
    }

    fun navigateToGoalSuccessDialog(goal: FinanceGoalEntity) {
        _navigateToGoalSuccess.value = Event(true)
        viewModelScope.launch(Dispatchers.IO) {
            addNewGoalUseCase(
                FinanceGoalEntity(
                    "historical",
                    goal.category,
                    goal.amountGoal,
                    goal.balance,
                    goal.interval,
                    goal.deadline,
                    goal.intervalAmount,
                    goal.name
                )
            )
        }
    }

    fun prepareCorrectViewPagerData(
        goalCategory: String,
    ): List<FinanceGoalEntity>? {
        return filterListOfGoal(goalCategory, "actual")
    }

    fun prepareHistoricalGoalData(category: String): List<FinanceGoalEntity>? {
        return filterListOfGoal(category, "historical")
    }

    private fun filterListOfGoal(
        goalCategory: String,
        goalType: String
    ): List<FinanceGoalEntity>? {
        return _allGoalsFromDatabase.value?.filter { it.category == goalCategory && it.type == goalType }
    }
}