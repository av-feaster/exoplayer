package com.prozect.exoplayer

import com.example.example.VideoApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiMethods {

//    var url: String
//        get() = "videos/?key=30324101-26205a82ff76c888817ae6b44"
//        set(value) = TODO()

    @GET("videos/?key=30324101-26205a82ff76c888817ae6b44&per_page=20")
    suspend fun getAllUsers(): Response<VideoApi>


}