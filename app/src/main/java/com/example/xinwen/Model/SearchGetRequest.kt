package com.example.xinwen.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.SearchData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.xinwen.db.Search
import retrofit2.Call
import retrofit2.Response

class SearchGetRequest {
    fun SearchRequest(requestLiveData: MutableLiveData<SearchData>){
        val retrofit = Retrofit.Builder()
                .baseUrl("https://v2.alapi.cn/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val request : SearchRequest= retrofit.create(SearchRequest :: class.java)
        request.getCall().enqueue(object : retrofit2.Callback<Search>{
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                response.body()?:return
                if(response.body()?.data!=null){
                    Log.d("Search","连接成功")
                    requestLiveData.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.d("Search","失败")
            }

        })
    }
}