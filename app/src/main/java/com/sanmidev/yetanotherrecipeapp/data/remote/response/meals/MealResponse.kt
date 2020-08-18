package com.sanmidev.yetanotherrecipeapp.data.remote.response.meals


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealResponse(
    @Json(name = "idMeal")
    val idMeal: String, // 52959
    @Json(name = "strMeal")
    val strMeal: String, // Baked salmon with fennel & tomatoes
    @Json(name = "strMealThumb")
    val strMealThumb: String // https://www.themealdb.com/images/media/meals/1548772327.jpg
)