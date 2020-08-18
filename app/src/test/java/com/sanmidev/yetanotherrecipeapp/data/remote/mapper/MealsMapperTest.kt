package com.sanmidev.yetanotherrecipeapp.data.remote.mapper

import com.google.common.truth.Truth
import com.sanmidev.yetanotherrecipeapp.DataUtils
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryModel
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealResponse
import org.junit.After
import org.junit.Before

import org.junit.Test

class MealsMapperTest {

    lateinit var CUT: MealsDBMapper
    lateinit var categoryTestData: Pair<CategoryResponse, CategoryModel>
    lateinit var categoryListTestData: Pair<CategoryListResponse, CategoryListModel>
    lateinit var mealListTestData: Pair<MealListResponse, MealListModel>
    lateinit var mealTestData: Pair<MealResponse, MealModel>


    @Before
    fun setUp() {
        CUT = MealsDBMapper()
        categoryTestData = DataUtils.provideCategory()
        categoryListTestData = DataUtils.provideCategories()
        mealTestData = DataUtils.provideMeal()
        mealListTestData = DataUtils.provideMealsTestData()
    }

    @Test
    fun mapCategoryResponseToCategoryModel_Category_shouldReturnCategoryModel() {
        //GIVEN
        val expectedCategoryResponse: CategoryResponse = categoryTestData.first
        val expectedCategoryModel: CategoryModel = categoryTestData.second

        //WHEN
        val actualCategoryModel = CUT.mapCategoryResponseToCategoryModel(expectedCategoryResponse)

        //THEN
        Truth.assertThat(actualCategoryModel.id).isEqualTo(expectedCategoryModel.id)
        Truth.assertThat(actualCategoryModel.category).isEqualTo(expectedCategoryModel.category)
        Truth.assertThat(actualCategoryModel.description)
            .isEqualTo(expectedCategoryModel.description)
        Truth.assertThat(actualCategoryModel.image).isEqualTo(expectedCategoryModel.image)
    }


    @Test
    fun mapCategoriesResponseListToCategoriesModelList_CategoryList_shouldReturnListOfCategoryModel() {
        //GIVEN
        val expectedCategoriesResponseList: CategoryListResponse = categoryListTestData.first
        val expectedCategoryModelList: CategoryListModel = categoryListTestData.second

        //WHEN
        val actualCategoryListModel =
            CUT.mapCategoriesResponseListToCategoriesModelList(expectedCategoriesResponseList)

        //THEN
        Truth.assertThat(actualCategoryListModel.categories)
            .containsExactlyElementsIn(expectedCategoryModelList.categories)

    }

    @Test
    fun mapMealResponseToMealModel_shouldReturnMealModel() {
        //GIVEN
        val expectedMealsReponse = mealTestData.first
        val expectedMealsModel = mealTestData.second

        //WHEN
        val actualMealModel = CUT.mapMealResponseToMealModel(expectedMealsReponse)

        //THEN
        Truth.assertThat(actualMealModel.id).isEqualTo(expectedMealsModel.id)
        Truth.assertThat(actualMealModel.image).isEqualTo(expectedMealsModel.image)
        Truth.assertThat(actualMealModel.name).isEqualTo(expectedMealsModel.name)
    }


    @Test
    fun mapMealListResponseToMealListModel_shouldReturnMealListModel() {
        //GIVEN
        val expectedMealListResponse = mealListTestData.first
        val expectedMealListModel = mealListTestData.second

        //WHEN
        val actualMealListModel: MealListModel =
            CUT.mapMealListReponseToMealListModel(expectedMealListResponse)

        //THEN
        Truth.assertThat(actualMealListModel.meals)
            .containsExactlyElementsIn(expectedMealListModel.meals)
    }

    @After
    fun tearDown() {
    }
}