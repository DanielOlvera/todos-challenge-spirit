package com.danielolvera.spritichallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danielolvera.spritichallenge.repo.TodoRepo

class ViewModelFactory(private val repo: TodoRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodosViewModel::class.java)) {
            return TodosViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
