package com.example.xinwen.db

data class TouTiaoList(
    val code: Int,
    val `data`: TouTiaoData,
    val log_id: Long,
    val msg: String,
    val time: Int
)

data class TouTiaoData(
    val last_update: String,
    val list: List<TouTiaoItem>,
    val name: String
)

data class TouTiaoItem(
    val link: String,
    val other: String,
    val title: String
)