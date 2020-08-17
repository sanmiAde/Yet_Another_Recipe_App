package com.sanmidev.yetanotherrecipeapp

import com.github.javafaker.Faker
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryModel
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryResponse

object DataUtils {

    private val faker by lazy { Faker() }
    val categoriesData = provideCategories()

    fun provideCategory(): Pair<CategoryResponse, CategoryModel> {
        val id = faker.idNumber().valid()
        val category = faker.food().dish()
        val description = faker.book().publisher()
        val image = faker.internet().image()

        val categoryModel = CategoryModel(id, category, description, image)
        val categoryResponse =
            CategoryResponse(
                id,
                category,
                description,
                image
            )

        return Pair(categoryResponse, categoryModel)
    }

    fun provideCategories(): Pair<CategoryListResponse, CategoryListModel> {
        val categoryResponseList = mutableListOf<CategoryResponse>()
        val categoryModelList = mutableListOf<CategoryModel>()

        repeat(10) {
            val data = provideCategory()
            categoryResponseList.add(data.first)
            categoryModelList.add(data.second)
        }
        val categoryListResponse = CategoryListResponse(categoryResponseList)
        val categoryListModel = CategoryListModel(categoryModelList)
        return Pair(categoryListResponse, categoryListModel)

    }
}