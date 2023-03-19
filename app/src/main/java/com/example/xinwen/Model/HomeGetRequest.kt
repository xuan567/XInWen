package com.example.xinwen.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.Data
import com.example.xinwen.db.Home_attention_news
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeGetRequest {

    fun attentionRequest(requestLiveData: MutableLiveData<Data>)  {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://v2.alapi.cn/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request: HomeGetRequestAttention = retrofit.create(HomeGetRequestAttention::class.java)

        request.getCall("5tMzmrhXv4ogqTeA").enqueue(object : retrofit2.Callback<Home_attention_news?> {

            override fun onResponse(
                call: Call<Home_attention_news?>,
                response: Response<Home_attention_news?>
            ) {

                response.body()?:return
                if(response.body()?.`data` != null) {
                    requestLiveData.value = response.body()?.`data`
                    Log.d("attention","连接成功")
                }

            }

            override fun onFailure(call: Call<Home_attention_news?>, t: Throwable) {
                Log.d("attention","连接失败")
            }
        })

    }

}
