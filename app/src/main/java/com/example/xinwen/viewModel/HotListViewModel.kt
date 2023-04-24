package com.example.xinwen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.model.HotGetRequest
import com.example.xinwen.db.WeiBoData


class HotListViewModel:ViewModel(){
    private val _hotListLivaData = MutableLiveData<WeiBoData>()
    val hotListLivaData:LiveData<WeiBoData> = _hotListLivaData
    val hotGetRequest = HotGetRequest()

    fun hot(){
        hotGetRequest.hotGetRequest(_hotListLivaData)
    }

}