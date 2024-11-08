package com.danielolvera.spritichallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielolvera.spritichallenge.intent.TodoDataIntent
import com.danielolvera.spritichallenge.repo.TodoRepo
import com.danielolvera.spritichallenge.state.TodoState
import kotlinx.coroutines.launch

class TodosViewModel (private val repo: TodoRepo): ViewModel() {

    private val _todoState = MutableLiveData<TodoState>()
    val todoState : LiveData<TodoState> = _todoState

    fun handleIntent(intent: TodoDataIntent) {
        when(intent) {
            is TodoDataIntent.FetchData -> {
                fetchTodos()
            }
        }
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            try {
                repo.getApi().collect { state ->
                    _todoState.value = state
                }
            } catch (e: Exception) {
                _todoState.value = TodoState.Error("Failed to fetch todos")
            }
        }
    }
}