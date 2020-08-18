package com.sanmidev.yetanotherrecipeapp.data.remote.services

import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbService {

    @GET("/api/json/v1/1/categories.php")
    fun getCategories(): Single<CategoryListResponse>

    @GET("/api/json/v1/1/filter.php")
    fun getMeals(@Query("c") category: String): Single<MealListResponse>
}