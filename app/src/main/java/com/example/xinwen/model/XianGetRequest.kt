package com.example.xinwen.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.WeatherData
import com.example.xinwen.db.XianWeather
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class XianGetRequest {

    fun WeatherRequest(requestLiveData: MutableLiveData<WeatherData>){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v2.alapi.cn/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request:XianWeatherGetRequest = retrofit.create(XianWeatherGetRequest::class.java)

        request.getCall("5tMzmrhXv4ogqTeA").enqueue(object : retrofit2.Callback<XianWeather>{
            override fun onResponse(call: Call<XianWeather>, response: Response<XianWeather>) {
                response.body()?:return
                if(response.body()?.data != null){
                    requestLiveData.value = response.body()?.data
                    Log.d("Weather","Weather连接成功")
                }
            }

            override fun onFailure(call: Call<XianWeather>, t: Throwable) {
                Log.d("TAG","Weather连接失败")
            }

        })
    }
}