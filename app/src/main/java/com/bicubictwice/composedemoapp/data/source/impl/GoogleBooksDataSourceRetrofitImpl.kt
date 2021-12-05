package com.bicubictwice.composedemoapp.data.source.impl

import android.util.Log
import com.bicubictwice.composedemoapp.classTag
import com.bicubictwice.composedemoapp.data.model.BookInfo
import com.bicubictwice.composedemoapp.data.model.ResponseResult
import com.bicubictwice.composedemoapp.data.source.api.GoogleBooksDataSourceApi
import com.bicubictwice.composedemoapp.data.source.api.SearchFilter
import com.bicubictwice.composedemoapp.data.source.impl.retrofit.GoogleBooksService
import com.bicubictwice.composedemoapp.data.source.impl.retrofit.RetrofitHelper
import javax.inject.Inject

class GoogleBooksDataSourceRetrofitImpl @Inject constructor() : GoogleBooksDataSourceApi {

    private val service by lazy { RetrofitHelper.createService<GoogleBooksService>(BASE_URL) }

    override suspend fun search(
        query: String,
        filter: SearchFilter,
        startIndex: Int,
        maxResults: Int,
    ): ResponseResult<List<BookInfo>> {

        return try {
            val response = service.findVolumes(
                query = query,
                filter = filter.value,
                startIndex = startIndex,
                maxResults = maxResults,
            )
            Log.d(classTag, "response: ${response?.items?.size} / ${response?.totalItems}")

            // mapping dto to model

            ResponseResult.Success(response?.items?.map {
                BookInfo(
                    id = it.id ?: "",
                    title = it.volumeInfo?.title ?: "",
                    subtitle = it.volumeInfo?.subtitle ?: "",
                    authors = it.volumeInfo?.authors ?: emptyList(),
                    publishedDate = it.volumeInfo?.publishedDate ?: "",
                    pageCount = it.volumeInfo?.pageCount ?: 0,
                    language = it.volumeInfo?.language ?: "",
                    smallThumbnailLink = it.volumeInfo?.imageLinks?.smallThumbnail ?: "",
                    thumbnailLink = it.volumeInfo?.imageLinks?.thumbnail ?: "",
                    infoLink = it.volumeInfo?.infoLink ?: "",
                )
            } ?: emptyList())

        } catch (e: Exception) {
            Log.e(classTag, e.stackTraceToString())
            ResponseResult.Error(e)
        }
    }

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}
