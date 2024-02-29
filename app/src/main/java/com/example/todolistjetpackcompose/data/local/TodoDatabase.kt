package com.example.todolistjetpackcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistjetpackcompose.data.local.dao.TodoDao
import com.example.todolistjetpackcompose.data.local.dto.LocalTodoItem

@Database(
    entities = [LocalTodoItem::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {
    abstract val dao: TodoDao
}