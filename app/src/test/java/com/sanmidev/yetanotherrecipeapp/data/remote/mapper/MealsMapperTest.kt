package com.sanmidev.yetanotherrecipeapp.data.remote.mapper

import com.google.common.truth.Truth
import com.sanmidev.yetanotherrecipeapp.DataUtils
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryModel
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryListResponse
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryResponse
import org.junit.After
import org.junit.Before

import org.junit.Test

class MealsMapperTest {

    lateinit var CUT: MealsDBMapper
    lateinit var categoryTestData: Pair<CategoryResponse, CategoryModel>
    lateinit var categoryListTestData: Pair<CategoryListResponse, CategoryListModel>


    @Before
    fun setUp() {
        CUT = MealsDBMapper()
        categoryTestData = DataUtils.provideCategory()
        categoryListTestData = DataUtils.provideCategories()
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

    @After
    fun tearDown() {
    }
}