package com.example.todolistjetpackcompose.domain.useCases

import com.example.todolistjetpackcompose.core.utils.TodoUseCasesConstants
import com.example.todolistjetpackcompose.data.repository.TodoListRepo
import com.example.todolistjetpackcompose.domain.model.TodoItem
import com.example.todolistjetpackcompose.domain.util.InvalidTodoItemException
import com.example.todolistjetpackcompose.domain.util.SortingDirection
import com.example.todolistjetpackcompose.domain.util.TodoItemOrder
import javax.inject.Inject

class TodoUseCases @Inject constructor(
    private val repo: TodoListRepo
) {
    suspend fun addTodoItem(todoItem: TodoItem) {
        if (todoItem.title.isBlank() || todoItem.description.isBlank()) {
            throw InvalidTodoItemException(TodoUseCasesConstants.EMPTY_TITLE_OR_DESCRIPTION)
        }
        repo.addTodoItem(todoItem)
    }

    suspend fun updateTodoItem(todoItem: TodoItem) {
        if (todoItem.title.isBlank() || todoItem.description.isBlank()) {
            throw InvalidTodoItemException(TodoUseCasesConstants.EMPTY_TITLE_OR_DESCRIPTION)
        }
        repo.updateItem(todoItem)
    }

    suspend fun deleteTodoItem(todoItem: TodoItem) {
        repo.deleteItem(todoItem)
    }

    suspend fun toogleCompleteTodoItem(todoItem: TodoItem) {
        repo.updateItem(todoItem.copy(completed = !todoItem.completed))
    }

    suspend fun toogleArchiveTodoItem(todoItem: TodoItem) {
        repo.updateItem(todoItem.copy(archived = !todoItem.archived))
    }

    suspend fun getAllTodoItems(
        todoItemOrder: TodoItemOrder = TodoItemOrder.Time(SortingDirection.down, showArchives = true)
    ): TodoUseCaseResult {
        var items = repo.getAllItemFromDatabase()

        if (items.isEmpty()) {
            items = repo.getAllItems()
        }

        val filteredItem = if (todoItemOrder.showArchives) {
            items
        } else {
            items.filter { !it.archived}
        }

        return when(todoItemOrder.sortingDirection) {
            is SortingDirection.down -> {
                when (todoItemOrder) {
                    is TodoItemOrder.Title -> TodoUseCaseResult.Success(filteredItem.sortedByDescending { it.title.lowercase() })
                    is TodoItemOrder.Time -> TodoUseCaseResult.Success(filteredItem.sortedByDescending { it.timeStamp })
                    is TodoItemOrder.Completed -> TodoUseCaseResult.Success(filteredItem.sortedByDescending { it.completed })
                }
            }
            is SortingDirection.up -> {
                when (todoItemOrder) {
                    is TodoItemOrder.Title -> TodoUseCaseResult.Success(filteredItem.sortedBy { it.title.lowercase() })
                    is TodoItemOrder.Time -> TodoUseCaseResult.Success(filteredItem.sortedBy { it.timeStamp })
                    is TodoItemOrder.Completed -> TodoUseCaseResult.Success(filteredItem.sortedBy { it.completed })
                }
            }
        }
    }
}

sealed class TodoUseCaseResult {
    data class Success(val todoItems: List<TodoItem>): TodoUseCaseResult()
    data class Error(val message: String): TodoUseCaseResult()
}