package com.example.xinwen.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.xinwen.db.Recommend
import com.example.xinwen.db.Result
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecommendRequest {
    fun recommendRequest(requestLiveData:MutableLiveData<Result>){
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.jisuapi.com/")
                .build()
        val request : RecommendGetRequest = retrofit.create(RecommendGetRequest::class.java)
        request.getCall().enqueue(object :retrofit2.Callback<Recommend>{
            override fun onResponse(call: Call<Recommend>, response: Response<Recommend>) {
                response.body()?:return
                if(response.body()?.result!=null){

                    requestLiveData.value = response.body()?.result
                    Log.d("Recommend","Recommend连接成功")
                }
            }

            override fun onFailure(call: Call<Recommend>, t: Throwable) {
                Log.d("Recommend","Recommend连接失败")
            }


        })

    }

}