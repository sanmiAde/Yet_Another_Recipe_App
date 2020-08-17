package com.sanmidev.yetanotherrecipeapp

import com.sanmidev.yetanotherrecipeapp.data.remote.mapper.MealsDBMapper
import com.sanmidev.yetanotherrecipeapp.data.remote.repo.MeallDBRepositoryImpl
import com.sanmidev.yetanotherrecipeapp.utils.TestSchedulers
import okhttp3.mockwebserver.MockWebServer

object RepositoryUtils {

    fun provideMealsDbRepostory(mockWebServer: MockWebServer): MeallDBRepositoryImpl {

        val bakingRecipeService = NetworkUtils.provideMealDBService(mockWebServer)

        val moshi = NetworkUtils.moshi

        val mapper = MealsDBMapper()

        return MeallDBRepositoryImpl(
            bakingRecipeService,
            mapper
        )
    }

    fun provideTestSchedulers(): TestSchedulers {
        return TestSchedulers()
    }


}