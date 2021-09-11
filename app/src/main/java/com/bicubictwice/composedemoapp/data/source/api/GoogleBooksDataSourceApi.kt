package com.bicubictwice.composedemoapp.data.source.api

import com.bicubictwice.composedemoapp.data.model.BookInfo
import com.bicubictwice.composedemoapp.data.model.ResponseResult

interface GoogleBooksDataSourceApi {

    suspend fun search(
        query: String,
        filter: SearchFilter,
        startIndex: Int,
        maxResults: Int,
    ): ResponseResult<List<BookInfo>>
}
