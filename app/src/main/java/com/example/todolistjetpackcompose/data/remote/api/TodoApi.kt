package com.example.todolistjetpackcompose.data.remote.api


import com.example.todolistjetpackcompose.data.remote.dto.RemoteTodoItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface TodoApi {
    @GET("todo.json")
    suspend fun getAllTodos(): List<RemoteTodoItem>

    @GET("todo.json?orderBy=\"ID\"")
    suspend fun getSingleItemById(@Query("equalsTo") id: Int?): Map<String, RemoteTodoItem>

    @POST("todo.json")
    suspend fun addItem(@Body url: String, @Body updatedItem: RemoteTodoItem): Response<Unit>

    @PUT()
    suspend fun addNewItem(@Url url: String, @Body updatedItem: RemoteTodoItem): Response<Unit>

    @DELETE("todo/{id}.json")
    suspend fun deleteItemById(@Path("id") id: Int?): Response<Unit>

    @PUT("todo/{id}.json")
    suspend fun updateItemById(@Path("id") id: Int?, @Body todoItem: RemoteTodoItem): Response<Unit>
}