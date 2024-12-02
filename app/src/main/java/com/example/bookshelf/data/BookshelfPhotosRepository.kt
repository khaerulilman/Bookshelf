package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookshelfApiService

interface BookshelfPhotosRepository {
    suspend fun getBooks(): List<Book>
}

class NetworkBookshelfPhotosRepository(
    private val bookshelfApiService: BookshelfApiService
) : BookshelfPhotosRepository {
    override suspend fun getBooks(): List<Book> =
        bookshelfApiService.searchBooks().items
}