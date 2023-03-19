package com.example.xinwen.db

data class ZhiHuList(
    val code: Int,
    val `data`: ZhiHuData,
    val log_id: Long,
    val msg: String,
    val time: Int
)

data class ZhiHuData(
    val last_update: String,
    val list: List<ZhiHuItem>,
    val name: String
)

data class ZhiHuItem(
    val link: String,
    val other: String,
    val title: String
)