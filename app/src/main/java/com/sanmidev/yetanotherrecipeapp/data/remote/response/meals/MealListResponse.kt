package com.sanmidev.yetanotherrecipeapp.data.remote.response.meals


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealListResponse(
    @Json(name = "meals")
    val meals: List<MealResponse>
)