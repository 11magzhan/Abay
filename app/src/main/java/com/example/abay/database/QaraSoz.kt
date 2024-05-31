package com.example.abay.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qarasozder_table")
data class QaraSoz(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "favorite")
    var favorite: Int = 0,

    @ColumnInfo(name = "number")
    var number: Long
)
