package com.example.xinwen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.model.ClothesListRequest
import com.example.xinwen.db.ClothesData

class ClothesViewModel:ViewModel() {
    private val _clothesLiveData = MutableLiveData<ClothesData>()
    val clothesLiveData : LiveData<ClothesData> = _clothesLiveData
    val clothesRequest =  ClothesListRequest()
    fun clothes(){
        clothesRequest.clothesRequest(_clothesLiveData)
    }
}