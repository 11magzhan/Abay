package com.example.abay.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface QaraSozDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(qaraSoz: List<QaraSoz>)

    @Update
    suspend fun update(qaraSoz: QaraSoz)

    @Query("SELECT * from qarasozder_table WHERE id = :key")
    suspend fun get(key: Long): QaraSoz?

    @Query("DELETE FROM qarasozder_table WHERE id = :key")
    suspend fun delete(key: Long)

    @Query("SELECT * FROM qarasozder_table")
    fun getAll(): LiveData<List<QaraSoz>>

    @Query("SELECT * FROM qarasozder_table WHERE favorite = 1")
    fun getAllFavorites(): LiveData<List<QaraSoz>>

}