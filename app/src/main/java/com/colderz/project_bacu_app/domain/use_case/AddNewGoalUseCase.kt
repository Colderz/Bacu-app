package com.colderz.project_bacu_app.domain.use_case

import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.repository.DatabaseGoalRepository
import javax.inject.Inject

class AddNewGoalUseCase @Inject constructor(
    private val repository: DatabaseGoalRepository
) {
    suspend operator fun invoke(newGoal: FinanceGoalEntity) {
        repository.upsert(newGoal)
    }
}