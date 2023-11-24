package com.example.artstation.api

import com.example.artstation.model.ImageResponse
import com.example.artstation.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("/api/")
    suspend fun  imageSearch(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String= API_KEY
    ): Response<ImageResponse>
}