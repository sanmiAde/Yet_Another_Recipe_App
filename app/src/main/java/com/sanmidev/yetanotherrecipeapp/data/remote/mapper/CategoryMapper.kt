package com.sanmidev.yetanotherrecipeapp.data.remote.mapper

import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryModel
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryResponse
import javax.inject.Inject

class MealsDBMapper @Inject constructor() {

    fun mapCategoryResponseToCategoryModel(categoryResponse: CategoryResponse): CategoryModel {
        return with(categoryResponse) {
            CategoryModel(idCategory, strCategory, strCategoryDescription, strCategoryThumb)
        }
    }

    fun mapCategoriesResponseListToCategoriesModelList(categoryListResponse: CategoryListResponse): CategoryListModel {
        val categoryModelList =
            categoryListResponse.categories.map { categoryResponse: CategoryResponse ->
                mapCategoryResponseToCategoryModel(categoryResponse)
            }
        return CategoryListModel(categoryModelList)
    }
}