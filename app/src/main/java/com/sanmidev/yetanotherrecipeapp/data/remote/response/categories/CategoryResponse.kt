package com.sanmidev.yetanotherrecipeapp.data.remote.response.categories


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "idCategory")
    val idCategory: String, // 1
    @Json(name = "strCategory")
    val strCategory: String, // Beef
    @Json(name = "strCategoryDescription")
    val strCategoryDescription: String, // Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]
    @Json(name = "strCategoryThumb")
    val strCategoryThumb: String // https://www.themealdb.com/images/category/beef.png
)