package com.colderz.project_bacu_app.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "goals"
)
data class FinanceGoalEntity(
    val type: String,
    val category: String,
    val amountGoal: String,
    val balance: String,
    val interval: String,
    val deadline: String,
    val intervalAmount: String,
    @PrimaryKey
    val name: String
)
