package com.bicubictwice.composedemoapp.data.source.impl.retrofit.dto

import com.google.gson.annotations.Expose

class VolumesResponse {

    @Expose
    val kind: String = ""

    @Expose
    val totalItems: Int = 0

    @Expose
    val items: List<VolumeItem> = emptyList()

    class VolumeItem {

        @Expose
        val id: String = ""

        @Expose
        val volumeInfo: BookInfo? = null
    }

    class BookInfo {

        @Expose
        val title: String = ""

        @Expose
        val subtitle: String = ""

        @Expose
        val authors: List<String> = emptyList()

        @Expose
        val publishedDate: String = ""

        @Expose
        val pageCount: Int = 0

        @Expose
        val imageLinks: ImageLinks? = null

        @Expose
        val language: String = ""

        @Expose
        val infoLink: String = ""
    }

    class ImageLinks {

        @Expose
        val smallThumbnail: String = ""

        @Expose
        val thumbnail: String = ""
    }
}
