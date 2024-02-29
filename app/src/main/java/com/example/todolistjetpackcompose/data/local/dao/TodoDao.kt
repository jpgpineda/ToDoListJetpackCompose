package com.example.todolistjetpackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolistjetpackcompose.data.local.dto.LocalTodoItem

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAllTodoItems(): List<LocalTodoItem>

    @Query("SELECT * FROM todo_table WHERE id = :id")
    suspend fun getSingleTodoItemById(id: Int): LocalTodoItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllTodoItems(todos: List<LocalTodoItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodoItem(todo: LocalTodoItem): Long

    @Delete
    suspend fun deleteTodoItem(todo: LocalTodoItem)
}