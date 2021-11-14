package com.colderz.project_bacu_app.domain.repository

import androidx.lifecycle.LiveData
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity

interface DatabaseGoalRepository {

    suspend fun upsert(financeGoalEntity: FinanceGoalEntity)

    fun readAllData(): LiveData<List<FinanceGoalEntity>>

    suspend fun deleteGoal(financeGoalEntity: FinanceGoalEntity)
}