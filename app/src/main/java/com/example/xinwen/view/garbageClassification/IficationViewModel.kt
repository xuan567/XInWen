package com.example.xinwen.view.garbageClassification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xinwen.bean.GarbageRecognitionBean
import com.example.xinwen.network.ApiRetrofit
import com.example.xinwen.view.garbageClassification.IficationFragment.Companion.TAG
import kotlinx.coroutines.launch

class IficationViewModel : ViewModel() {

    private val _garbageText: MutableLiveData<GarbageRecognitionBean> = MutableLiveData()
    val garbageText: LiveData<GarbageRecognitionBean> = _garbageText

    private val _garbageImage: MutableLiveData<GarbageRecognitionBean> = MutableLiveData()
    val garbageImage: LiveData<GarbageRecognitionBean> = _garbageImage

    private val _garbageVideo: MutableLiveData<GarbageRecognitionBean> = MutableLiveData()
    val garbageVideo: LiveData<GarbageRecognitionBean> = _garbageVideo

    fun getGarbageTypeByText(word: String){

        viewModelScope.launch {
            val result = ApiRetrofit.getGarbageTypeByText().apiTextGarbageRecognition(ApiRetrofit.GARBAGE_API_KEY, word)
            _garbageText.value = result
        }
    }

    fun getGarbageTypeByBase64Image(base64Image: String) {
        viewModelScope.launch {
            try {
                val result = ApiRetrofit.getGarbageTypeByText().apiImageGarbageRecognition(ApiRetrofit.GARBAGE_API_KEY, base64Image)
                _garbageImage.value = result
            } catch (e: Exception) {
                Log.d(TAG, "getGarbageTypeByBase64Image: $e")
            }

        }
    }

    fun getGarbageTypeByVideo(say: String, format: String) {
        viewModelScope.launch {
            val result = ApiRetrofit.getGarbageTypeByText().apiVoiceGarbageRecognition(ApiRetrofit.GARBAGE_API_KEY, say, format)
            _garbageVideo.value = result
        }
    }

}