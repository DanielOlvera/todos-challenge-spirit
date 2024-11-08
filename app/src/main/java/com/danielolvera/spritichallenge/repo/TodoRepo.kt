package com.danielolvera.spritichallenge.repo

import com.danielolvera.spritichallenge.model.retrofit.ApiClient
import com.danielolvera.spritichallenge.state.TodoState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepo(private val apiService: ApiClient) {

    fun getApi(): Flow<TodoState> = flow {
        emit(TodoState.Loading)
        try {
            val response = apiService.getTodos()
            emit(TodoState.Success(response))
        } catch (e: Exception) {
            emit(TodoState.Error(e.message ?: "Unknown error. How?"))
        }
    }
}