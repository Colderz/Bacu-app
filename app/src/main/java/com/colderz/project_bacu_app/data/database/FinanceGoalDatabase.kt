package com.colderz.project_bacu_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity

@Database(entities = [FinanceGoalEntity::class], version = 1)
abstract class FinanceGoalDatabase : RoomDatabase() {
    abstract fun financeGoalDao(): FinanceGoalDao

    companion object {

        val DATABASE_NAME: String = "goal_db"
    }
}