package com.danielolvera.spritichallenge.model

import retrofit2.http.GET

interface ApiClient {

    @GET("todos")
    suspend fun getTodos(): List<TodosData>
}