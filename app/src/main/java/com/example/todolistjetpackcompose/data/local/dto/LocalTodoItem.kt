package com.example.todolistjetpackcompose.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class LocalTodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val timeStamp: Long,
    val completed: Boolean,
    val archived: Boolean
)
