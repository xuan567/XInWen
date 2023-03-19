package com.example.xinwen.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HotGetRequest {
    fun hotGetRequest(requestLiveData: MutableLiveData<WeiBoData>){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v2.alapi.cn/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request : HotListGetRequest = retrofit.create(HotListGetRequest::class.java)
        request.getCall("5tMzmrhXv4ogqTeA").enqueue(object : retrofit2.Callback<WeiBoList>{
            override fun onResponse(call: Call<WeiBoList>, response: Response<WeiBoList>) {
                response.body()?:return

                if(response.body()?.data!=null){
                    requestLiveData.value = response.body()?.data
                    Log.d("Hot","Hot连接成功")
                }
            }

            override fun onFailure(call: Call<WeiBoList>, t: Throwable) {
                Log.d("Hot","Hot连接失败")
            }


        })
    }
}