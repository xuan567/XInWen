package com.example.xinwen.model

import com.example.xinwen.db.Recommend
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RecommendGetRequest {
    @POST("news/get")
    @FormUrlEncoded
    fun getCall(@Field("appkey")appkey:String = "07952e732c1fc364",@Field("channel")channel:String="新闻",
    @Field("num")num:Int = 40):Call<Recommend>

}