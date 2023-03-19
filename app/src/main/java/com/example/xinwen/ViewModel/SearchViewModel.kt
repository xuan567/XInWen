package com.example.xinwen.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.Model.SearchGetRequest
import com.example.xinwen.Model.SearchRequest
import com.example.xinwen.db.SearchData
import com.example.xinwen.db.TouTiaoData

class SearchViewModel : ViewModel(){
    private val _searchLiveData = MutableLiveData<SearchData>()
    val searchLiveData : LiveData<SearchData>  = _searchLiveData
    val searchRequest = SearchGetRequest()
    fun search(){
        searchRequest.SearchRequest(_searchLiveData)
    }
}