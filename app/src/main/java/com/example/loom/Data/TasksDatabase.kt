package com.example.loom.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tasks::class], version = 1, exportSchema = true)
abstract class TasksDatabase: RoomDatabase() {

    abstract fun taskDao(): TasksDAO
}