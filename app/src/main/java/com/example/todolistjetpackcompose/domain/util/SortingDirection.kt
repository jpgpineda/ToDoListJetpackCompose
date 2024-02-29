package com.example.todolistjetpackcompose.domain.util

sealed class SortingDirection {
    object up: SortingDirection()
    object down: SortingDirection()
}