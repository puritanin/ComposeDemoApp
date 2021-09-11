package com.bicubictwice.composedemoapp.data.source.api

enum class SearchFilter(val value: String, val description: String) {

    PARTIAL(
        value = "partial",
        description = "Returns results where at least parts of the text are previewable.",
    ),

    FULL(
        value = "full",
        description = "Only returns results where all of the text is viewable.",
    ),

    FREE_EBOOKS(
        value = "free-ebooks",
        description = "Only returns results that are free Google eBooks.",
    ),

    PAID_EBOOKS(
        value = "paid-ebooks",
        description = "Only returns results that are Google eBooks with a price.",
    ),

    E_BOOKS(
        value = "ebooks",
        description = "Only returns results that are Google eBooks, paid or free. Examples of non-eBooks would be publisher content that is available in limited preview and not for sale, or magazines.",
    ),
}
