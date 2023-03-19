package com.example.xinwen.db

data class Search(
    val code: Int,
    val `data`: SearchData,
    val log_id: Long,
    val msg: String,
    val time: Int
)

data class SearchData(
    val creator: String,
    val from: String,
    val hitokoto: String,
    val id: Int,
    val type: String
)