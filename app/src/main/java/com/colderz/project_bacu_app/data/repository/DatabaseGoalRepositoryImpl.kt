package com.colderz.project_bacu_app.data.repository

import androidx.lifecycle.LiveData
import com.colderz.project_bacu_app.data.database.FinanceGoalDao
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.repository.DatabaseGoalRepository
import javax.inject.Inject

class DatabaseGoalRepositoryImpl @Inject constructor(
    private val dao: FinanceGoalDao
) : DatabaseGoalRepository {
    override suspend fun upsert(financeGoalEntity: FinanceGoalEntity) {
        dao.upsert(financeGoalEntity)
    }

    override fun readAllData(): LiveData<List<FinanceGoalEntity>> = dao.getAllGoals()

    override suspend fun deleteGoal(financeGoalEntity: FinanceGoalEntity) {
        dao.deleteGoal(financeGoalEntity)
    }
}