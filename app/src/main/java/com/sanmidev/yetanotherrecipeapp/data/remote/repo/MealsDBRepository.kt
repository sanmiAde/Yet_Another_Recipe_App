package com.sanmidev.yetanotherrecipeapp.data.remote.repo

import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryListModel
import io.reactivex.Single

interface MealsDBRepository {
    fun getCategories(): Single<CategoryListModel>

    fun getMeals(category: String): Single<MealListModel>
}