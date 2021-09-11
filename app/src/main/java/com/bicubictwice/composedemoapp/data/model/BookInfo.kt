package com.bicubictwice.composedemoapp.data.model

data class BookInfo(
    val id: String,
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val publishedDate: String,
    val pageCount: Int,
    val language: String,
    val smallThumbnailLink: String,
    val thumbnailLink: String,
    val infoLink: String,
)
