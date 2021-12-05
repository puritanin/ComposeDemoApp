package com.bicubictwice.composedemoapp.data.source.impl.retrofit.dto

import com.google.gson.annotations.SerializedName

class VolumesResponse {

    @SerializedName("kind")
    var kind: String? = ""

    @SerializedName("totalItems")
    var totalItems: Int? = 0

    @SerializedName("items")
    var items: List<VolumeItem>? = emptyList()

    class VolumeItem {

        @SerializedName("id")
        var id: String? = ""

        @SerializedName("volumeInfo")
        var volumeInfo: BookInfo? = null
    }

    class BookInfo {

        @SerializedName("title")
        var title: String? = ""

        @SerializedName("subtitle")
        var subtitle: String? = ""

        @SerializedName("authors")
        var authors: List<String>? = emptyList()

        @SerializedName("publishedDate")
        var publishedDate: String? = ""

        @SerializedName("pageCount")
        var pageCount: Int? = 0

        @SerializedName("imageLinks")
        var imageLinks: ImageLinks? = null

        @SerializedName("language")
        var language: String? = ""

        @SerializedName("infoLink")
        var infoLink: String? = ""
    }

    class ImageLinks {

        @SerializedName("smallThumbnail")
        var smallThumbnail: String? = ""

        @SerializedName("thumbnail")
        var thumbnail: String? = ""
    }
}
