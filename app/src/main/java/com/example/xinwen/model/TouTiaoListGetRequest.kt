package com.example.xinwen.model

import com.example.xinwen.db.TouTiaoList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TouTiaoListGetRequest {
    @POST("tophub/get")
    @FormUrlEncoded
    fun getCall(@Field("token")token:String = "5tMzmrhXv4ogqTeA",@Field("type")type:String = "toutiao"): Call<TouTiaoList>
}