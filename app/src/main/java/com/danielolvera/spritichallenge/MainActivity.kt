package com.danielolvera.spritichallenge

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.danielolvera.spritichallenge.intent.TodoDataIntent
import com.danielolvera.spritichallenge.model.ApiServiceClient
import com.danielolvera.spritichallenge.repo.TodoRepo
import com.danielolvera.spritichallenge.state.TodoState
import com.danielolvera.spritichallenge.ui.theme.SpritiChallengeTheme
import com.danielolvera.spritichallenge.viewmodel.TodosViewModel
import com.danielolvera.spritichallenge.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TodosViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val todoRepo = TodoRepo(ApiServiceClient.apiService)

        val vmFactory = ViewModelFactory(todoRepo)
        viewModel = ViewModelProvider(this, vmFactory)[TodosViewModel::class.java]
        viewModel.handleIntent(TodoDataIntent.FetchData)

        setContent {
            SpritiChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    Greeting(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: TodosViewModel) {
    val todos by viewModel.todoState.observeAsState()

    when (todos) {
        is TodoState.Loading -> {
            CircularProgressIndicator()
        }
        is TodoState.Success -> {
            val todo = (todos as TodoState.Success).todos

            LazyColumn {
                items(todo) { item ->
                    Card {
                        Text(text = item.title)
                        Text(text = item.completed.toString())
                    }
                }
            }
        }
        is TodoState.Error -> {
            Text(text = "Error: Something happened")
        }
        else -> {
            Text(text = "Error: How it landed here? $todos")
        }
    }

}