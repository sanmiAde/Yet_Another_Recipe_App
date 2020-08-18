package com.sanmidev.yetanotherrecipeapp

import com.sanmidev.yetanotherrecipeapp.DataUtils.categoriesData
import com.sanmidev.yetanotherrecipeapp.DataUtils.mealDetailData
import com.sanmidev.yetanotherrecipeapp.data.remote.response.categories.CategoryListResponseJsonAdapter
import com.sanmidev.yetanotherrecipeapp.data.remote.response.mealDetail.MealDetailListResponseJsonAdapter
import com.sanmidev.yetanotherrecipeapp.data.remote.response.meals.MealListResponseJsonAdapter
import com.sanmidev.yetanotherrecipeapp.data.remote.services.MealDbService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

object NetworkUtils {
    const val CATEGORIES_LIST_PATH = "/api/json/v1/1/categories.php"
    const val MEAL_PATH_QUERY_PARAM = "Seafood"
    const val MEAL_PATH = "/api/json/v1/1/filter.php?c=$MEAL_PATH_QUERY_PARAM"
    const val MEAL_DETAIL_QUERY_PARAM = "1234"
    const val MEAL_DETAIL_PATH = "/api/json/v1/1/lookup.php?i=$MEAL_DETAIL_QUERY_PARAM"

    val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val categoryListResponseJsonAdapter = CategoryListResponseJsonAdapter(moshi)

    private val mealListJsonAdapter = MealListResponseJsonAdapter(moshi)

    private val mealDetailJsonAdapter = MealDetailListResponseJsonAdapter(moshi)

    fun provideRetrofit(mockWebServer: MockWebServer): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun provideMealDBService(mockWebServer: MockWebServer): MealDbService {
        return provideRetrofit(mockWebServer).create(MealDbService::class.java)
    }

    fun getMockWebserverDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    request.path?.contains(CATEGORIES_LIST_PATH)!! -> {
                        val categoryListResponse = categoriesData.first

                        val successJson =
                            categoryListResponseJsonAdapter.toJson(categoryListResponse)

                        MockResponse().setBody(
                            successJson
                        ).setResponseCode(HttpURLConnection.HTTP_OK)
                    }

                    request.path?.contains(MEAL_PATH)!! -> {
                        val mealListResponse = DataUtils.mealsData.first

                        val successJson = mealListJsonAdapter.toJson(mealListResponse)


                        MockResponse().setBody(
                            successJson
                        ).setResponseCode(HttpURLConnection.HTTP_OK)
                    }

                    request.path?.contains(MEAL_DETAIL_PATH)!! -> {
                        val mealDetailListResponse = mealDetailData.first

                        val successJson = mealDetailJsonAdapter.toJson(mealDetailListResponse)

                        MockResponse().setBody(
                            successJson
                        ).setResponseCode(HttpURLConnection.HTTP_OK)
                    }

                    else -> {
                        MockResponse().setBody(
                            "Content Not Found"
                        ).setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    }
                }
            }
        }
    }


}