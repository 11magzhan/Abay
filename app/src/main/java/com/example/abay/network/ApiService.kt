package com.example.abay.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "https://api.birkitap.kz/public/qarasoz/"

private val loggingInterceptor = HttpLoggingInterceptor { message ->
    Log.d("OkHttp", message)
}.apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient
    .Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("get")
    suspend fun getQaraSozByNumber(@Query("number") number: Int): QaraSozResponse

    @POST("list")
    suspend fun getAllQaraSoz(@Body emptyBody: Map<String, String> = emptyMap()): QaraSozListResponseList
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}