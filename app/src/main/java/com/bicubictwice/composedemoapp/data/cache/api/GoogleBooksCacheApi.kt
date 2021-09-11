package com.bicubictwice.composedemoapp.data.cache.api

import com.bicubictwice.composedemoapp.data.model.BookInfo

interface GoogleBooksCacheApi {

    val size: Int

    fun append(items: List<BookInfo>): Int

    fun clear()

    fun getByIndex(index: Int): BookInfo?
}
