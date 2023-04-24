package com.example.xinwen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.model.HomeGetRequest
import com.example.xinwen.db.Data

class HomeAttentionViewModel : ViewModel() {

    private val _attentionLiveData = MutableLiveData<Data>()
    val attentionLiveData : LiveData<Data> = _attentionLiveData
    val homeGetRequest =  HomeGetRequest()

    fun attention(){
        homeGetRequest.attentionRequest(_attentionLiveData)
    }
}