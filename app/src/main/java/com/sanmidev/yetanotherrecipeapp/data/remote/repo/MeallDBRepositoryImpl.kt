package com.sanmidev.yetanotherrecipeapp.data.remote.repo

import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.remote.mapper.MealsDBMapper
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryListResponse
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

    //TODO do you need the response body itself. you can simply allow retorfit to parse it for you since you don't need to know the status code
    //Todo is it posible to use the json file for testing

}