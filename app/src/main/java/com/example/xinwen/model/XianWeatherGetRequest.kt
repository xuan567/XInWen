package com.example.xinwen.model

import com.example.xinwen.db.XianWeather
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface XianWeatherGetRequest {
    @POST("weather")
    @FormUrlEncoded
    fun getCall( @Field("token")token:String, @Field("location")location:String="西安"): Call<XianWeather>
}