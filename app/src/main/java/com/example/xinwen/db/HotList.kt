package com.example.xinwen.db

data class WeiBoList(
    val code: Int,
    val `data`: WeiBoData,
    val log_id: Long,
    val msg: String,
    val time: Int
)

data class WeiBoData(
    val last_update: String,
    val list: List<Item>,
    val name: String
)

data class Item(
    val link: String,
    val other: String,
    val title: String
)