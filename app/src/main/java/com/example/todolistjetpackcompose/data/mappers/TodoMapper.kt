package com.example.todolistjetpackcompose.data.mappers

import com.example.todolistjetpackcompose.data.local.dto.LocalTodoItem
import com.example.todolistjetpackcompose.data.remote.dto.RemoteTodoItem
import com.example.todolistjetpackcompose.domain.model.TodoItem

fun TodoItem.toLocalTodo() = LocalTodoItem(
    id = id,
    title = title,
    description = description,
    timeStamp = timeStamp,
    completed = completed,
    archived = archived
)

fun TodoItem.toRemoteTodo() = RemoteTodoItem(
    id = id,
    title = title,
    description = description,
    timeStamp = timeStamp,
    completed = completed,
    archived = archived
)

fun LocalTodoItem.toTodoItem() = TodoItem(
    id = id,
    title = title,
    description = description,
    timeStamp = timeStamp,
    completed = completed,
    archived = archived
)

fun LocalTodoItem.toRemoteTodoItem() = RemoteTodoItem(
    id = id,
    title = title,
    description = description,
    timeStamp = timeStamp,
    completed = completed,
    archived = archived
)

fun RemoteTodoItem.toTodoItem() = TodoItem(
    id = id,
    title = title,
    description = description,
    timeStamp = timeStamp,
    completed = completed,
    archived = archived
)

fun RemoteTodoItem.toLocalTodoItem() = LocalTodoItem(
    id = id,
    title = title,
    description = description,
    timeStamp = timeStamp,
    completed = completed,
    archived = archived
)

fun List<TodoItem>.toLocalTodoListItem(): List<LocalTodoItem> {
    return this.map { todo ->
        LocalTodoItem(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            timeStamp = todo.timeStamp,
            completed = todo.completed,
            archived = todo.archived
        )
    }
}

fun List<TodoItem>.toRemoteTodoListItem(): List<RemoteTodoItem> {
    return this.map { todo ->
        RemoteTodoItem(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            timeStamp = todo.timeStamp,
            completed = todo.completed,
            archived = todo.archived
        )
    }
}

fun List<LocalTodoItem>.toTodoListItemFromLocal(): List<TodoItem> {
    return this.map { todo ->
        TodoItem(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            timeStamp = todo.timeStamp,
            completed = todo.completed,
            archived = todo.archived
        )
    }
}

fun List<LocalTodoItem>.toRemoteTodoListItemFromLocal(): List<RemoteTodoItem> {
    return this.map { todo ->
        RemoteTodoItem(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            timeStamp = todo.timeStamp,
            completed = todo.completed,
            archived = todo.archived
        )
    }
}

fun List<RemoteTodoItem>.toLocalTodoListItemFromRemote(): List<LocalTodoItem> {
    return this.map { todo ->
        LocalTodoItem(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            timeStamp = todo.timeStamp,
            completed = todo.completed,
            archived = todo.archived
        )
    }
}

fun List<RemoteTodoItem>.toTodoListItemFromRemote(): List<TodoItem> {
    return this.map { todo ->
        TodoItem(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            timeStamp = todo.timeStamp,
            completed = todo.completed,
            archived = todo.archived
        )
    }
}