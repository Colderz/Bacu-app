package com.colderz.project_bacu_app.domain.use_case

import android.util.Log
import androidx.lifecycle.LiveData
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.domain.repository.DatabaseGoalRepository
import javax.inject.Inject

class GetAllGoalsUseCase @Inject constructor(
    private val repository: DatabaseGoalRepository
) {
    operator fun invoke(): LiveData<List<FinanceGoalEntity>> {
        return repository.readAllData()
    }
}
