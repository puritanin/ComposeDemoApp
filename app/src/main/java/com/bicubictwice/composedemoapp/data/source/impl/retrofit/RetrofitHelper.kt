package com.bicubictwice.composedemoapp.data.source.impl.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getClient(baseUrl: String): Retrofit {
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    inline fun <reified T> createService(client: Retrofit): T {
        return client.create(T::class.java)
    }

    inline fun <reified T> createService(baseUrl: String): T {
        return createService(getClient(baseUrl))
    }
}
