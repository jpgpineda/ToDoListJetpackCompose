package com.example.todolistjetpackcompose.data.repository

import com.example.todolistjetpackcompose.domain.model.TodoItem

interface TodoListRepo {
    suspend fun getAllItems(): List<TodoItem>
    suspend fun getAllItemFromDatabase(): List<TodoItem>
    suspend fun getAllItemsFromRemote()
    suspend fun getSingleItemById(id: Int): TodoItem?
    suspend fun addTodoItem(todoItem: TodoItem)
    suspend fun updateItem(todoItem: TodoItem)
    suspend fun deleteItem(todoItem: TodoItem)
}