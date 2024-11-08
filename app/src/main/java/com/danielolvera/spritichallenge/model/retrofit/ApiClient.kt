package com.danielolvera.spritichallenge.model.retrofit

import com.danielolvera.spritichallenge.model.data.TodosData
import retrofit2.http.GET

interface ApiClient {

    @GET("todos")
    suspend fun getTodos(): List<TodosData>
}