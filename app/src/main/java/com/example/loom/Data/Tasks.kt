package com.example.loom.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var task: String,
    var complete: Boolean = false
)
