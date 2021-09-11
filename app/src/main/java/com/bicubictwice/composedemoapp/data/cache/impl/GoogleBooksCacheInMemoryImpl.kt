package com.bicubictwice.composedemoapp.data.cache.impl

import android.util.Log
import com.bicubictwice.composedemoapp.classTag
import com.bicubictwice.composedemoapp.data.cache.api.GoogleBooksCacheApi
import com.bicubictwice.composedemoapp.data.model.BookInfo
import java.util.*
import javax.inject.Inject

class GoogleBooksCacheInMemoryImpl @Inject constructor() : GoogleBooksCacheApi {

    private val cachedItems = LinkedList<BookInfo>()

    override val size: Int
        @Synchronized get() = cachedItems.size

    @Synchronized
    override fun append(items: List<BookInfo>): Int {
        var appended = 0
        items.forEach {
            if (!cachedItems.contains(it)) {
                cachedItems.add(it)
                appended++
            }
        }
        Log.d(classTag, "appended: $appended / ${cachedItems.size}")
        return appended
    }

    @Synchronized
    override fun clear() {
        cachedItems.clear()
    }

    @Synchronized
    override fun getByIndex(index: Int): BookInfo? {
        return cachedItems.getOrNull(index)
    }
}
