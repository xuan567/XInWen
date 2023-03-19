package com.example.xinwen.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xinwen.Model.XianGetRequest
import com.example.xinwen.db.WeatherData

class XianWeatherViewModel :ViewModel()  {
    private val _weatherLiveData = MutableLiveData<WeatherData>()
    val weatherLiveData : LiveData<WeatherData> = _weatherLiveData
    val xianGetRequest  = XianGetRequest()

    fun weather(){
        xianGetRequest.WeatherRequest(_weatherLiveData)
    }
}