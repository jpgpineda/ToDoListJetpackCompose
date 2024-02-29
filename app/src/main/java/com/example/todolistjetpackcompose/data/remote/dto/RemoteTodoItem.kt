package com.example.todolistjetpackcompose.data.remote.dto

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RemoteTodoItem(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Timestamp")
    val timeStamp: Long,
    @SerializedName("Completed")
    val completed: Boolean,
    @SerializedName("Archived")
    val archived: Boolean
)
