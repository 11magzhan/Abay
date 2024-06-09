package com.example.abay.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("get")
    suspend fun getQaraSozByNumber(@Query("number") number: Int): QaraSozResponse

    @POST("list")
    suspend fun getAllQaraSoz(@Body emptyBody: Map<String, String> = emptyMap()): QaraSozListResponseList
}