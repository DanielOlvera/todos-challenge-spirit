package com.danielolvera.spritichallenge.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceClient {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: ApiClient by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }
}