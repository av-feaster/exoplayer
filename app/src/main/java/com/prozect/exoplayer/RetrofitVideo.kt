package com.prozect.exoplayer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitVideo {

    val baseUrl = "https://pixabay.com/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()


    }
}