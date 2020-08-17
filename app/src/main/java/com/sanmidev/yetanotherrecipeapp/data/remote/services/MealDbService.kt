package com.sanmidev.yetanotherrecipeapp.data.remote.services

import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MealDbService {

    @GET("/api/json/v1/1/categories.php")
    fun getCategories(): Single<CategoryListResponse>
}