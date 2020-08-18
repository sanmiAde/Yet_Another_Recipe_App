package com.sanmidev.yetanotherrecipeapp.data.remote.mapper

import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryModel
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealResponse
import javax.inject.Inject

class MealsDBMapper @Inject constructor() {

    fun mapCategoryResponseToCategoryModel(categoryResponse: CategoryResponse): CategoryModel {
        return with(categoryResponse) {
            CategoryModel(
                idCategory,
                strCategory,
                strCategoryDescription,
                strCategoryThumb
            )
        }
    }

    fun mapCategoriesResponseListToCategoriesModelList(categoryListResponse: CategoryListResponse): CategoryListModel {
        val categoryModelList =
            categoryListResponse.categories.map { categoryResponse: CategoryResponse ->
                mapCategoryResponseToCategoryModel(categoryResponse)
            }
        return CategoryListModel(
            categoryModelList
        )
    }

    fun mapMealResponseToMealModel(mealResponse: MealResponse): MealModel {
        return with(mealResponse) {
            MealModel(idMeal, strMeal, strMealThumb)
        }
    }

    fun mapMealListReponseToMealListModel(mealListResponse: MealListResponse): MealListModel {
        val mealsModelList = mealListResponse.meals.map { mapMealResponseToMealModel(it) }
        return MealListModel(mealsModelList)
    }


}