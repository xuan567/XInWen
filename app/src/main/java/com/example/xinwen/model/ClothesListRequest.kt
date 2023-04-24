package com.example.xinwen.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.ClothesData
import com.example.xinwen.db.ClothesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClothesListRequest {

    fun clothesRequest(requestLiveData: MutableLiveData<ClothesData>){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v2.alapi.cn/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request : ClothListGetRequest = retrofit.create(ClothListGetRequest::class.java)
        request.getCall().enqueue(object : retrofit2.Callback<ClothesList>{
            override fun onResponse(call: Call<ClothesList>, response: Response<ClothesList>) {
                response.body()?:return
                if(response.body()?.data!=null){
                    Log.d("Weather","Clothes连接成功")
                    requestLiveData.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ClothesList>, t: Throwable) {
                Log.d("Weather","Clothes连接成功")
            }

        })
    }
}