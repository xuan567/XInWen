package com.example.xinwen.Model

import com.example.xinwen.db.ClothesList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ClothListGetRequest {
    @POST("weather/life")
    @FormUrlEncoded
    fun getCall(@Field("token")token:String = "5tMzmrhXv4ogqTeA",@Field("location")location:String = "西安"):Call<ClothesList>
}