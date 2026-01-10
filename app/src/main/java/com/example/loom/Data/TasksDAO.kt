package com.example.loom.Data

import android.R
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TasksDAO {

    @Query("SELECT * FROM Tasks")
    fun getAllTasks(): Flow<List<Tasks>>

    @Query("INSERT INTO Tasks (task) VALUES (:task)")
    suspend fun addTask(task: String)

    @Query("DELETE FROM Tasks WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("UPDATE Tasks SET complete = :complete WHERE id = :taskId")
    suspend fun updateCompleteTask(taskId: Int, complete: Boolean)

    @Query("UPDATE Tasks SET task = :newTask WHERE id = :taskId")
    suspend fun updateTask(taskId: Int, newTask: String)

    @Query("SELECT * FROM Tasks WHERE id = :taskId")
    suspend fun getEditTask(taskId: Int): Tasks
}
