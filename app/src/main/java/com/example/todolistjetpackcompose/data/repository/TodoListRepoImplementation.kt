package com.example.todolistjetpackcompose.data.repository

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.todolistjetpackcompose.data.di.IODispatcher
import com.example.todolistjetpackcompose.data.local.dao.TodoDao
import com.example.todolistjetpackcompose.data.mappers.toLocalTodo
import com.example.todolistjetpackcompose.data.mappers.toLocalTodoListItemFromRemote
import com.example.todolistjetpackcompose.data.mappers.toRemoteTodo
import com.example.todolistjetpackcompose.data.mappers.toTodoItem
import com.example.todolistjetpackcompose.data.mappers.toTodoListItemFromLocal
import com.example.todolistjetpackcompose.data.remote.api.TodoApi
import com.example.todolistjetpackcompose.domain.model.TodoItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class TodoListRepoImplementation @Inject constructor(
    private val dao: TodoDao,
    private val api: TodoApi,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : TodoListRepo {
    override suspend fun getAllItems(): List<TodoItem> {
        getAllItemsFromRemote()
        return dao.getAllTodoItems().toTodoListItemFromLocal()
    }

    override suspend fun getAllItemFromDatabase(): List<TodoItem> {
        return dao.getAllTodoItems().toTodoListItemFromLocal()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAllItemsFromRemote() {
        return withContext(dispatcher) {
            try {
                refreshRoomCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP", "Error: No data from remote")
                        if (isCacheEmpty()) {
                            Log.e("Cache", "Error: No data from room cache")
                            throw Exception("Error: Devide offline and no data from cache")
                        }
                    }
                    else -> { throw e}
                }
            }
        }
    }

    private suspend fun refreshRoomCache() {
        val remoteResponse = api.getAllTodos()
        dao.addAllTodoItems(remoteResponse.toLocalTodoListItemFromRemote())
    }

    private fun isCacheEmpty(): Boolean {
        return dao.getAllTodoItems().isEmpty()
    }

    override suspend fun getSingleItemById(id: Int): TodoItem? {
        return dao.getSingleTodoItemById(id)?.toTodoItem()
    }

    override suspend fun addTodoItem(todoItem: TodoItem) {
        val newId = dao.addTodoItem(todoItem.toLocalTodo())
        val id = newId.toInt()
        val url = "todo/$id.json"
        api.addNewItem(url, todoItem.toRemoteTodo().copy(id = id))
    }

    override suspend fun updateItem(todoItem: TodoItem) {
        dao.addTodoItem(todoItem.toLocalTodo())
        api.updateItemById(todoItem.id, todoItem.toRemoteTodo())
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun deleteItem(todoItem: TodoItem) {
        try {
            val response = api.deleteItemById(todoItem.id)
            if (response.isSuccessful) {
                Log.i("API_DELETE", "RESPONSE SUCCESSFULL")
            } else {
                Log.e("API_DELETE", "RESPONSE UNSUCCESSFULL")
                Log.e("API_DELETE", response.message())
            }
        } catch (e: Exception) {
            when (e) {
                is  UnknownHostException, is ConnectException, is HttpException -> {
                    Log.e("HTTPM", "Error: Could not delete")
                }
                else -> {
                    throw e
                }
            }
        }
    }
}