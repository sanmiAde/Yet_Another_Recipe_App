package com.sanmidev.yetanotherrecipeapp.data.remote.response.mealDetail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealDetailListResponse(
    @Json(name = "meals")
    val meals: List<MealDetailResponse>
)