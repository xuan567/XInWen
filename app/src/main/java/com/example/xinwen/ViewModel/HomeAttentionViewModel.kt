package com.example.xinwen.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.Model.HomeGetRequest
import com.example.xinwen.Model.XianGetRequest
import com.example.xinwen.db.Data
import com.example.xinwen.db.XianWeather

class HomeAttentionViewModel : ViewModel() {

    private val _attentionLiveData = MutableLiveData<Data>()
    val attentionLiveData : LiveData<Data> = _attentionLiveData
    val homeGetRequest =  HomeGetRequest()

    fun attention(){
        homeGetRequest.attentionRequest(_attentionLiveData)
    }
}