package com.example.todolistjetpackcompose.presentation.toDoList

import com.example.todolistjetpackcompose.domain.model.TodoItem
import com.example.todolistjetpackcompose.domain.util.SortingDirection
import com.example.todolistjetpackcompose.domain.util.TodoItemOrder

data class TodoListState(
    val todoItems: List<TodoItem> = emptyList(),
    val todoItemOrder: TodoItemOrder = TodoItemOrder.Time(SortingDirection.down, true),
    val isLoading: Boolean = true,
    val error: String? = null
)
