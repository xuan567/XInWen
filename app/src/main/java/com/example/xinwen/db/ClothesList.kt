package com.example.xinwen.db

data class ClothesList(
    val code: Int,
    val `data`: ClothesData,
    val log_id: Long,
    val msg: String,
    val time: Int
)

data class ClothesData(
    val basic: ClothesBasic,
    val lifestyle: List<Lifestyle>,
    val status: String,
    val update: ClothesUpdate
)

data class ClothesBasic(
    val admin_area: String,
    val cid: String,
    val cnty: String,
    val lat: String,
    val location: String,
    val lon: String,
    val parent_city: String,
    val tz: String
)

data class Lifestyle(
    val brf: String,
    val txt: String,
    val type: String
)

data class ClothesUpdate(
    val loc: String,
    val utc: String
)