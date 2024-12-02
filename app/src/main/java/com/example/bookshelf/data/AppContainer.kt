package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val bookshelfPhotosRepository: BookshelfPhotosRepository
}

class DefaultAppContainer : AppContainer {
    // Base URL for Google Books API
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    // Initialize Retrofit with JSON configuration
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()

    // Initialize BooksApiService
    private val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }

    // Initialize BooksRepository using retrofitService
    override val bookshelfPhotosRepository: BookshelfPhotosRepository by lazy {
        NetworkBookshelfPhotosRepository(retrofitService)
    }
}