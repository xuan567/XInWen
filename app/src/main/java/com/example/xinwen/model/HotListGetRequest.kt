package com.example.xinwen.model


import com.example.xinwen.db.WeiBoList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HotListGetRequest {
    @POST("tophub/get?")
    @FormUrlEncoded
    fun getCall(@Field("token")token:String,@Field("type")type:String = "weibo"):Call<WeiBoList>
}