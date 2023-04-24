package com.example.xinwen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.model.SearchGetRequest
import com.example.xinwen.db.SearchData

class SearchViewModel : ViewModel(){
    private val _searchLiveData = MutableLiveData<SearchData>()
    val searchLiveData : LiveData<SearchData>  = _searchLiveData
    val searchRequest = SearchGetRequest()
    fun search(){
        searchRequest.SearchRequest(_searchLiveData)
    }
}