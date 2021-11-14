package com.colderz.project_bacu_app.di

import android.content.Context
import androidx.room.Room
import com.colderz.project_bacu_app.data.database.FinanceGoalDao
import com.colderz.project_bacu_app.data.database.FinanceGoalDatabase
import com.colderz.project_bacu_app.data.repository.DatabaseGoalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideFinanceGoalDb(@ApplicationContext context: Context): FinanceGoalDatabase {
        return Room.databaseBuilder(
            context,
            FinanceGoalDatabase::class.java,
            FinanceGoalDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFinanceGoalDAO(financeGoalDatabase: FinanceGoalDatabase): FinanceGoalDao {
        return financeGoalDatabase.financeGoalDao()
    }

    @Singleton
    @Provides
    fun provideDatabaseGoalRepository(financeGoalDao: FinanceGoalDao): DatabaseGoalRepositoryImpl = DatabaseGoalRepositoryImpl(financeGoalDao)
}