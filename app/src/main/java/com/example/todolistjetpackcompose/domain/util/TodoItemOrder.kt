package com.example.todolistjetpackcompose.domain.util

sealed class TodoItemOrder(
    val sortingDirection: SortingDirection,
    val showArchives: Boolean
) {
    class Title(sortingDirection: SortingDirection, showArchives: Boolean): TodoItemOrder(sortingDirection, showArchives)
    class Time(sortingDirection: SortingDirection, showArchives: Boolean): TodoItemOrder(sortingDirection, showArchives)
    class Completed(sortingDirection: SortingDirection, showArchives: Boolean): TodoItemOrder(sortingDirection, showArchives)

    fun copy(sortingDirection: SortingDirection, showArchives: Boolean): TodoItemOrder {
        return when(this) {
            is Title -> Title(sortingDirection, showArchives)
            is Time -> Time(sortingDirection, showArchives)
            is Completed -> Completed(sortingDirection, showArchives)
        }
    }
}