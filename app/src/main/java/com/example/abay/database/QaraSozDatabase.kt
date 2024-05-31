package com.example.abay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QaraSoz::class], version = 1, exportSchema = false)
abstract class QaraSozDatabase : RoomDatabase(){

    abstract fun qaraSozDao(): QaraSozDao

    companion object {

        @Volatile
        private var INSTANCE: QaraSozDatabase? = null

        fun getDatabase(context: Context): QaraSozDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QaraSozDatabase::class.java,
                    "qarasozder_database").build()
                INSTANCE = instance
                instance
            }
        }
    }