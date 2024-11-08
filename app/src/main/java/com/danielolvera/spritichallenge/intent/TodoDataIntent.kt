package com.danielolvera.spritichallenge.intent

sealed class TodoDataIntent {
    data object FetchData: TodoDataIntent()
}