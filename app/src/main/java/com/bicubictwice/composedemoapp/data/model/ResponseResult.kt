package com.bicubictwice.composedemoapp.data.model

sealed class ResponseResult<out T> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Error<out T>(val exception: Exception) : ResponseResult<T>()
}
