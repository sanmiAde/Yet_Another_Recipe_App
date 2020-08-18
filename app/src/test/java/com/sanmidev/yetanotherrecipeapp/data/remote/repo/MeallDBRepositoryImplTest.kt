package com.sanmidev.yetanotherrecipeapp.data.remote.repo

import com.google.common.truth.Truth
import com.sanmidev.yetanotherrecipeapp.DataUtils
import com.sanmidev.yetanotherrecipeapp.NetworkUtils
import com.sanmidev.yetanotherrecipeapp.RepositoryUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MeallDBRepositoryImplTest {

    lateinit var CUT: MeallDBRepositoryImpl

    @get:Rule
    val mockWebServer = MockWebServer()

    private lateinit var categoryDispatcher: Dispatcher

    private lateinit var mealDispatcher: Dispatcher

    private val compositeDisposable = CompositeDisposable()

    @Before
    fun setUp() {

        CUT = RepositoryUtils.provideMealsDbRepostory(mockWebServer)
        categoryDispatcher = NetworkUtils.getCategoriesMockWebserverDispatcher()
        mealDispatcher = NetworkUtils.getMealsMockWebserverDispatcher()
    }

    @After
    fun tearDown() {
        compositeDisposable.clear()
    }

    @Test
    fun getCategories_shouldCallCorrectAPI_whenRequestIsMade() {
        //GIVEN
        mockWebServer.dispatcher = categoryDispatcher

        //WHEN
        val testObserver = CUT.getCategories().test()
        testObserver.addTo(compositeDisposable)
        val request = mockWebServer.takeRequest()

        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        Truth.assertThat(request.path).isEqualTo(NetworkUtils.CATEGORIES_LIST_PATH)
        Truth.assertThat(request.method).isEqualTo("GET")

    }


    @Test
    fun getCategories_shouldReturnListOfCategories_whenRequestIsSuccessful() {
        //GIVEN
        mockWebServer.dispatcher = categoryDispatcher
        val expectedCategoriesData = DataUtils.categoriesData.second

        //WHEN
        val testObserver = CUT.getCategories().test()
        testObserver.addTo(compositeDisposable)
        val categories = testObserver.values()!![0]

        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        Truth.assertThat(categories.categories)
            .containsExactlyElementsIn(expectedCategoriesData.categories)
    }


    @Test
    fun getMeals_shouldCallTheRightAPIWithQueryParams_whenApiIsCalled() {
        //GIVEN
        mockWebServer.dispatcher = mealDispatcher


        //WHEN
        val testObserver = CUT.getMeals(NetworkUtils.MEAL_PATH_QUERY_PARAM).test()
        testObserver.addTo(compositeDisposable)
        val request = mockWebServer.takeRequest()

        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        Truth.assertThat(request.path).isEqualTo(NetworkUtils.MEAL_PATH)
        Truth.assertThat(request.method).isEqualTo("GET")
        Truth.assertThat(request.requestUrl!!.querySize).isEqualTo(1)
    }


    @Test
    fun getMeals_shouldReturnListOfMeals_whenRequestIsSuccessful() {
        //GIVEN
        mockWebServer.dispatcher = mealDispatcher
        val expectedMealModel = DataUtils.mealsData.second

        //WHEN
        val testObserver = CUT.getMeals(NetworkUtils.MEAL_PATH_QUERY_PARAM).test()
        testObserver.addTo(compositeDisposable)
        val actualMeals = testObserver.values()!![0]

        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        Truth.assertThat(actualMeals.meals).containsExactlyElementsIn(expectedMealModel.meals)
    }


}