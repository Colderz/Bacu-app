package com.colderz.project_bacu_app.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity

@Dao
interface FinanceGoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(financeGoalEntity: FinanceGoalEntity)

    @Query("SELECT * FROM goals")
    fun getAllGoals(): LiveData<List<FinanceGoalEntity>>

    @Delete
    suspend fun deleteGoal(financeGoalEntity: FinanceGoalEntity)
}