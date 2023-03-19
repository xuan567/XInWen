package com.example.xinwen.db


data class Home_attention_news(
    val code: Int,
    val `data`: Data,
    val log_id: Long,
    val msg: String,
    val time: Int
)

data class Data(
    val date: String,
    val head_image: String,
    val image: String,
    val news: List<String>,
    val weiyu: String
)