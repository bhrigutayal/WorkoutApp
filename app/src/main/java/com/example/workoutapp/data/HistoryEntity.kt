package com.example.workoutapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "history-table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = false)
    val date: String
)
