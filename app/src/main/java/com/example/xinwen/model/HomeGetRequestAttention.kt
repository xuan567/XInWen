package com.example.xinwen.model

import com.example.xinwen.db.Home_attention_news
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HomeGetRequestAttention {
    @POST("zaobao")
    @FormUrlEncoded
    fun getCall(@Field("token")token:String,@Field("format")format:String="json"):Call<Home_attention_news>
}