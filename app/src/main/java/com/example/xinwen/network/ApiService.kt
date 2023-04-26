package com.example.xinwen.network

import com.example.xinwen.bean.GarbageRecognitionBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    /**
     * 垃圾分类-文字
     *
     * 例：
     * 请求URI: (GET)https://apis.tianapi.com/lajifenlei/index?key=ef3f0c87a61c043a23642a443f02c541&word=眼镜
     *
     */
    @GET("lajifenlei/index")
    suspend fun apiTextGarbageRecognition(@Query("key")  key: String,
                                          @Query("word") word: String): GarbageRecognitionBean

    /**
     * 垃圾分类-图片
     *
     * 例：
     * 请求URI: (POST)https://apis.tianapi.com/imglajifenlei/index
     */
    @FormUrlEncoded
    @POST("imglajifenlei/index")
    suspend fun apiImageGarbageRecognition(@Field("key") key: String,
                                           @Field("img") base64Img: String): GarbageRecognitionBean

    /**
     * 垃圾分类-语音
     *
     *  请求URI: (POST)https://apis.tianapi.com/voicelajifenlei/index
        请求参数: {
            "key": "ef3f0c87a61c043a23642a443f02c541",
            "say": "二进制流",
            "format": "wav"
        }
     *
     */
    @FormUrlEncoded
    @POST("imglajifenlei/index")
    suspend fun apiVoiceGarbageRecognition(@Field("key") key: String,
                                           @Field("say") say: String,
                                           @Field("format") format: String): GarbageRecognitionBean
}