package com.example.xinwen.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.Model.TouTiaoGetRequest
import com.example.xinwen.db.TouTiaoData

class TouTiaoViewModel :ViewModel(){
    private val _touTiaoLiveData = MutableLiveData<TouTiaoData>()
    val touTiaoLiveData : LiveData<TouTiaoData>  = _touTiaoLiveData
    val touTiaoGetRequest = TouTiaoGetRequest()
    fun toutiao(){
        touTiaoGetRequest.toutiaoRequest(_touTiaoLiveData)
    }
}