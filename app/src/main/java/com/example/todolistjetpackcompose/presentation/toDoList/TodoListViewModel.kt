package com.example.todolistjetpackcompose.presentation.toDoList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todolistjetpackcompose.data.di.IODispatcher
import com.example.todolistjetpackcompose.domain.model.TodoItem
import com.example.todolistjetpackcompose.domain.useCases.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoUseCase: TodoUseCases,
    @IODispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _state = mutableStateOf(TodoListState())
    val state: androidx.compose.runtime.State<TodoListState> = _state
    private var undoTodoItem: TodoItem? = null
    private val errorHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
        _state.value = _state.value.copy(
            error = e.message,
            isLoading = false
        )
    }


}