package com.bicubictwice.composedemoapp.data.source.impl.retrofit

import com.bicubictwice.composedemoapp.data.source.impl.retrofit.dto.VolumesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksService {

    @GET("volumes")
    suspend fun findVolumes(
        @Query("q") query: String,
        @Query("filter") filter: String,
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int,
    ): VolumesResponse?
}
