package com.example.xinwen.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.Model.ZhihuGetRequest
import com.example.xinwen.db.ZhiHuData

class ZhiHuViewModel : ViewModel(){
    private val _zhihuLiveData = MutableLiveData<ZhiHuData>()
    val zhihuLiveData:LiveData<ZhiHuData> = _zhihuLiveData
    val zhihuGetRequest = ZhihuGetRequest()
    fun zhihu(){
        zhihuGetRequest.zhihuRequest(_zhihuLiveData)
    }

}