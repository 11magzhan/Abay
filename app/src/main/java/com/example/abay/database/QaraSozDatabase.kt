package com.example.abay.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QaraSoz::class], version = 1, exportSchema = false)
abstract class QaraSozDatabase : RoomDatabase(){

    abstract fun qaraSozDao(): QaraSozDao
}