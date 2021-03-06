package com.sanmidev.yetanotherrecipeapp

import com.github.javafaker.Faker
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.mealDetail.MealDetailModel
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.mealDetail.MealDetailListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.mealDetail.MealDetailResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealResponse

object DataUtils {

    private val faker by lazy { Faker() }

    val categoriesData = provideCategories()
    val mealsData = provideMealsTestData()
    val mealDetailData = provideMealDetailData()


    fun provideCategory(): Pair<CategoryResponse, CategoryModel> {
        val id = faker.idNumber().valid()
        val category = faker.food().dish()
        val description = faker.book().publisher()
        val image = faker.internet().image()

        val categoryModel =
            CategoryModel(
                id,
                category,
                description,
                image
            )
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
        val categoryListResponse =
            CategoryListResponse(
                categoryResponseList
            )
        val categoryListModel =
            CategoryListModel(
                categoryModelList
            )
        return Pair(categoryListResponse, categoryListModel)
    }


    fun provideMeal(): Pair<MealResponse, MealModel> {
        val id = faker.idNumber().valid()
        val name = faker.food().dish()
        val image = faker.internet().image()

        val mealModel = MealModel(id, name, image)
        val mealResponse = MealResponse(id, name, image)

        return Pair(mealResponse, mealModel)
    }


    fun provideMealsTestData(): Pair<MealListResponse, MealListModel> {
        val mealsResponses = mutableListOf<MealResponse>()
        val mealsModels = mutableListOf<MealModel>()

        repeat(10) {
            val data = provideMeal()
            mealsResponses.add(data.first)
            mealsModels.add(data.second)
        }
        val mealsResponseList = MealListResponse(mealsResponses)
        val mealListModel = MealListModel(mealsModels)

        return Pair(mealsResponseList, mealListModel)
    }


    fun provideMealDetailData(): Triple<MealDetailListResponse, MealDetailResponse, MealDetailModel> {

        val id = faker.idNumber().valid()
        val name = faker.name().firstName()
        val image = faker.internet().image()
        val instruction = faker.address().buildingNumber()
        val youtubeLink = faker.internet().image()


        val mealDetailResponse = MealDetailResponse(
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().breed(),
            faker.animal().name(),
            faker.dog().age(),
            strYoutube = youtubeLink,
            idMeal = id,
            strInstructions = instruction,
            strMealThumb = image,
            strMeal = name

        )

        val mealDetailModel = MealDetailModel(id, name, image, instruction, youtubeLink)

        val mealDetailListResponse = MealDetailListResponse(listOf(mealDetailResponse))

        return Triple(mealDetailListResponse, mealDetailResponse, mealDetailModel)


    }


}