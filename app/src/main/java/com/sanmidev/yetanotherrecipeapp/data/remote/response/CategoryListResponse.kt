package com.sanmidev.yetanotherrecipeapp.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryListResponse(
    @Json(name = "categories")
    val categories: List<CategoryResponse>
)