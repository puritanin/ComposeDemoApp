package com.bicubictwice.composedemoapp.ui.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bicubictwice.composedemoapp.classTag
import com.bicubictwice.composedemoapp.data.model.BookInfo
import com.bicubictwice.composedemoapp.data.model.ResponseResult
import com.bicubictwice.composedemoapp.data.repository.api.GoogleBooksRepositoryApi
import com.bicubictwice.composedemoapp.data.source.api.SearchFilter

class BooksPagingSource(
    private val query: String,
    private val filter: SearchFilter,
    private val googleBooksRepositoryApi: GoogleBooksRepositoryApi,
) : PagingSource<Int, BookViewEntity>() {

    override fun getRefreshKey(state: PagingState<Int, BookViewEntity>): Int? {
        Log.d(classTag, "getRefreshKey: anchorPosition=${state.anchorPosition}")
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookViewEntity> {
        Log.d(classTag, "load: key=${params.key}, loadSize=${params.loadSize}")

        val pageIndex = params.key ?: 1

        val response = googleBooksRepositoryApi.getItemsByPage(
            query = query,
            filter = filter,
            pageSize = params.loadSize,
            pageIndex = pageIndex,
        )

        return when (response) {
            is ResponseResult.Error -> LoadResult.Error(response.exception)
            is ResponseResult.Success -> LoadResult.Page(
                data = response.data.map { mapDataToView(it) },
                prevKey = if (pageIndex > 1) pageIndex - 1 else null,
                nextKey = if (response.data.isEmpty()) null else pageIndex + 1,
            )
        }
    }

    private fun mapDataToView(bookInfo: BookInfo): BookViewEntity {
        return BookViewEntity(
            title = bookInfo.title,
            authors = bookInfo.authors.joinToString("\n"),
            secondaryInfo = listOf(
                if (bookInfo.publishedDate.isNotBlank()) bookInfo.publishedDate else "",
                bookInfo.language.uppercase(),
                if (bookInfo.pageCount != 0) "${bookInfo.pageCount} p." else "",
            ).filter { it.isNotBlank() }.joinToString(", "),
            thumbnailLink = bookInfo.smallThumbnailLink,
            infoLink = bookInfo.infoLink,
        )
    }
}
