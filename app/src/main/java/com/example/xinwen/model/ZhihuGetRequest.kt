package com.example.xinwen.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.ZhiHuData
import com.example.xinwen.db.ZhiHuList
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ZhihuGetRequest {
    fun zhihuRequest(requestLiveData: MutableLiveData<ZhiHuData>){
        val retrofit = Retrofit.Builder()
                .baseUrl("https://v2.alapi.cn/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val request : ZhiHuListGetRequest = retrofit.create(ZhiHuListGetRequest::class.java)
        request.getCall().enqueue(object :retrofit2.Callback<ZhiHuList>{
            override fun onResponse(call: Call<ZhiHuList>, response: Response<ZhiHuList>) {
                response.body()?:return
                if(response.body()?.data!=null){
                    requestLiveData.value=response.body()?.data
                    Log.d("ZhiHu","ZhiHu连接成功")
                }
            }

            override fun onFailure(call: Call<ZhiHuList>, t: Throwable) {
                Log.d("ZhiHu","ZhiHu连接失败")
            }

        })

    }
}