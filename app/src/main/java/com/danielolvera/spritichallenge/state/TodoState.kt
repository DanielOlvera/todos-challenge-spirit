package com.danielolvera.spritichallenge.state

import com.danielolvera.spritichallenge.model.data.TodosData

sealed class TodoState {
    data object  Loading: TodoState()
    data class Success(val todos: List<TodosData>): TodoState()
    data class Error(val errorMessage: String): TodoState()
}