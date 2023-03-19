package com.example.xinwen.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.TouTiaoData
import com.example.xinwen.db.TouTiaoList
import com.example.xinwen.db.ZhiHuList
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TouTiaoGetRequest {
    fun toutiaoRequest(requestLiveData : MutableLiveData<TouTiaoData> ){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v2.alapi.cn/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request : TouTiaoListGetRequest = retrofit.create(TouTiaoListGetRequest::class.java)
        request.getCall().enqueue(object :retrofit2.Callback<TouTiaoList>{
            override fun onResponse(call: Call<TouTiaoList>, response: Response<TouTiaoList>) {
                response.body()?:return
                if(response.body()?.data!=null){
                    requestLiveData.value = response.body()?.data
                    Log.d("TouTiao","TouTiao连接成功")
                }
            }

            override fun onFailure(call: Call<TouTiaoList>, t: Throwable) {
                Log.d("TouTiao","TouTiao连接失败")
            }

        })

    }
}