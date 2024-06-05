package com.example.abay.di

import android.content.Context
import androidx.room.Room
import com.example.abay.database.QaraSozDao
import com.example.abay.database.QaraSozDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QaraSozDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            QaraSozDatabase::class.java,
            "qarasozder_database"
        ).build()
    }

    @Provides
    fun provideQaraSozDao(database: QaraSozDatabase): QaraSozDao {
        return database.qaraSozDao()
    }
}