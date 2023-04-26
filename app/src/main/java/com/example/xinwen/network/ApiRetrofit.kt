package com.example.xinwen.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



object ApiRetrofit{
    const val GARBAGE_API_KEY = "ef3f0c87a61c043a23642a443f02c541"
    private const val GARBAGE_API_BASEURL = "https://apis.tianapi.com/"

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    fun getGarbageTypeByText(): ApiService {
        return Retrofit.Builder()
            .baseUrl(GARBAGE_API_BASEURL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun getGarbageTypeByImage(): ApiService {
        return Retrofit.Builder()
            .baseUrl(GARBAGE_API_BASEURL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}
