package com.example.todolistjetpackcompose.domain.model

data class TodoItem(
    val id: Int,
    val title: String,
    val description: String,
    val timeStamp: Long,
    val completed: Boolean,
    val archived: Boolean
)
