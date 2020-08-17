package com.sanmidev.yetanotherrecipeapp

import com.sanmidev.yetanotherrecipeapp.DataUtils.categoriesData
import com.sanmidev.yetanotherrecipeapp.data.remote.response.CategoryListResponseJsonAdapter
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

    val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val categoryListResponseJsonAdapter = CategoryListResponseJsonAdapter(moshi)

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