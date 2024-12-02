package com.example.bookshelf.network

import com.example.bookshelf.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String = "programming",
        @Query("maxResults") maxResults: Int = 10
    ): BookSearchResponse
}