package com.bicubictwice.composedemoapp.data.repository.api

import com.bicubictwice.composedemoapp.data.model.BookInfo
import com.bicubictwice.composedemoapp.data.model.ResponseResult
import com.bicubictwice.composedemoapp.data.source.api.SearchFilter

interface GoogleBooksRepositoryApi {

    suspend fun getItemsByPage(
        query: String,
        filter: SearchFilter,
        pageSize: Int,
        pageIndex: Int,
    ): ResponseResult<List<BookInfo>>
}
