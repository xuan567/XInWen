package com.example.xinwen.model

import com.example.xinwen.db.Search
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SearchRequest {
    @POST("hitokoto")
    @FormUrlEncoded
    fun getCall(@Field("token")token:String = "5tMzmrhXv4ogqTeA",@Field("type")type:String = "d"
    ,@Field("format")format:String = "json"):Call<Search>
}