package com.colderz.project_bacu_app.domain.use_case

import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.repository.DatabaseGoalRepository
import javax.inject.Inject

class DeleteGoalUseCase @Inject constructor(
    private val repository: DatabaseGoalRepository
) {
    suspend operator fun invoke(goal: FinanceGoalEntity) {
        repository.deleteGoal(goal)
    }
}