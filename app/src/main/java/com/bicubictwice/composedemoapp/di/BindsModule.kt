package com.bicubictwice.composedemoapp.di

import com.bicubictwice.composedemoapp.data.cache.api.GoogleBooksCacheApi
import com.bicubictwice.composedemoapp.data.cache.impl.GoogleBooksCacheInMemoryImpl
import com.bicubictwice.composedemoapp.data.repository.api.GoogleBooksRepositoryApi
import com.bicubictwice.composedemoapp.data.repository.impl.GoogleBooksRepositoryImpl
import com.bicubictwice.composedemoapp.data.source.api.GoogleBooksDataSourceApi
import com.bicubictwice.composedemoapp.data.source.impl.GoogleBooksDataSourceRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Singleton
    @Binds
    abstract fun bindGoogleBooksSourceApi(
        googleBooksSourceRetrofitImpl: GoogleBooksDataSourceRetrofitImpl
    ): GoogleBooksDataSourceApi

    @Singleton
    @Binds
    abstract fun bindGoogleBooksCacheApi(
        googleBooksCacheInMemoryImpl: GoogleBooksCacheInMemoryImpl
    ): GoogleBooksCacheApi

    @Singleton
    @Binds
    abstract fun bindGoogleBooksRepositoryApi(
        googleBooksRepositoryImpl: GoogleBooksRepositoryImpl
    ): GoogleBooksRepositoryApi
}
