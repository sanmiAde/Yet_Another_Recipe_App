package com.sanmidev.yetanotherrecipeapp.data.remote.repo

import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.remote.mapper.MealsDBMapper
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.services.MealDbService
import io.reactivex.Single
import javax.inject.Inject


class MeallDBRepositoryImpl @Inject constructor(
    private val mealDbService: MealDbService,
    private val mapper: MealsDBMapper
) : MealsDBRepository {


    override fun getCategories(): Single<CategoryListModel> {
        return mealDbService.getCategories().map { categoryListResponse: CategoryListResponse ->
            mapper.mapCategoriesResponseListToCategoriesModelList(categoryListResponse)
        }
    }

    override fun getMeals(category: String): Single<MealListModel> {
        return mealDbService.getMeals(category).map { mealListReponse: MealListResponse ->
            mapper.mapMealListReponseToMealListModel(mealListReponse)
        }
    }


}