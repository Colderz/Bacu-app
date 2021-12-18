package com.colderz.project_bacu_app.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable
