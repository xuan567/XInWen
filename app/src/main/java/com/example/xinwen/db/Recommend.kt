package com.example.xinwen.db

data class Recommend(
    val msg: String,
    val result: Result,
    val status: Int
)

data class Result(
    val channel: String,
    val list: List<ListItem>,
    val num: Int
)

data class ListItem(
    val category: String,
    val content: String,
    val pic: String,
    val src: String,
    val time: String,
    val title: String,
    val url: String,
    val weburl: String
)