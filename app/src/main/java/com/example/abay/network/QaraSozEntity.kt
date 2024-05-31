package com.example.abay.network

import com.squareup.moshi.Json

data class QaraSozResponse(
    @Json(name = "result_code") val resultCode: Int,
    @Json(name = "result_msg") val resultMsg: String,
    @Json(name = "data") val data: QaraSozEntity
)

data class QaraSozListResponseList(
    @Json(name = "result_code") val resultCode: Int,
    @Json(name = "result_msg") val resultMsg: String,
    @Json(name = "data") val data: List<QaraSozEntity>
)

data class QaraSozEntity(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "text") val text: String,
    @Json(name = "number") val number: Long
)