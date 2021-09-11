package com.bicubictwice.composedemoapp.data.repository.impl

import com.bicubictwice.composedemoapp.data.cache.api.GoogleBooksCacheApi
import com.bicubictwice.composedemoapp.data.model.BookInfo
import com.bicubictwice.composedemoapp.data.model.ResponseResult
import com.bicubictwice.composedemoapp.data.repository.api.GoogleBooksRepositoryApi
import com.bicubictwice.composedemoapp.data.source.api.GoogleBooksDataSourceApi
import com.bicubictwice.composedemoapp.data.source.api.SearchFilter
import java.util.*
import javax.inject.Inject

class GoogleBooksRepositoryImpl @Inject constructor(
    private val googleBooksDataSourceApi: GoogleBooksDataSourceApi,
    private val googleBooksCacheApi: GoogleBooksCacheApi,
) : GoogleBooksRepositoryApi {

    @Volatile
    private var lastQuery: String = ""

    @Volatile
    private var lastFilter: SearchFilter? = null

    override suspend fun getItemsByPage(
        query: String,
        filter: SearchFilter,
        pageSize: Int,
        pageIndex: Int
    ): ResponseResult<List<BookInfo>> {

        if (query.isBlank() || pageIndex < 1 || pageSize < 1) {
            return ResponseResult.Error(IllegalArgumentException())
        }

        if (lastQuery != query || lastFilter != filter) {
            googleBooksCacheApi.clear()
            lastQuery = query
            lastFilter = filter
        }

        val offset = (pageIndex - 1) * pageSize

        while (googleBooksCacheApi.size < offset + pageSize) {

            // Google API behavior is to give little out of order
            // maxResults maximum is 40
            val result = googleBooksDataSourceApi.search(
                query = query,
                filter = filter,
                startIndex = googleBooksCacheApi.size,
                maxResults = pageSize.coerceIn(1, 40),
            )

            when (result) {
                is ResponseResult.Error -> {
                    return ResponseResult.Error(result.exception)
                }
                is ResponseResult.Success -> {
                    val appendedCount = googleBooksCacheApi.append(result.data)
                    if (result.data.isEmpty() || appendedCount == 0) break
                }
            }
        }

        val items = LinkedList<BookInfo>()
        repeat(pageSize) { index ->
            googleBooksCacheApi.getByIndex(offset + index)?.let {
                items.add(it)
            }
        }
        return ResponseResult.Success(items)
    }
}
