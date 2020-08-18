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

    private lateinit var dispatcher: Dispatcher


    private val compositeDisposable = CompositeDisposable()

    @Before
    fun setUp() {

        CUT = RepositoryUtils.provideMealsDbRepostory(mockWebServer)
        dispatcher = NetworkUtils.getMockWebserverDispatcher()

    }

    @After
    fun tearDown() {
        compositeDisposable.clear()
    }

    @Test
    fun getCategories_shouldCallCorrectAPI_whenRequestIsMade() {
        //GIVEN
        mockWebServer.dispatcher = dispatcher

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
        mockWebServer.dispatcher = dispatcher
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
        mockWebServer.dispatcher = dispatcher


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
        mockWebServer.dispatcher = dispatcher
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

    @Test
    fun getMealDetail_shouldCallTheRightAPIWithQueryParams_whenApiIsCalled() {
        //GIVEN
        mockWebServer.dispatcher = dispatcher

        //WHEN
        val testObserver = CUT.getMealDetail(NetworkUtils.MEAL_DETAIL_QUERY_PARAM).test()
        testObserver.addTo(compositeDisposable)
        val request = mockWebServer.takeRequest()

        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        Truth.assertThat(request.path).isEqualTo(NetworkUtils.MEAL_DETAIL_PATH)
        Truth.assertThat(request.method).isEqualTo("GET")
        Truth.assertThat(request.requestUrl!!.query).contains("i=1234")
        Truth.assertThat(request.requestUrl!!.querySize).isEqualTo(1)
    }

    @Test
    fun getMealDetail_houldReturnMealDetail_whenRequestIsSuccessful() {
        //GIVEN
        mockWebServer.dispatcher = dispatcher
        val expectedMealDetailModel = DataUtils.mealDetailData.third

        //WHEN
        val testObserver = CUT.getMealDetail(NetworkUtils.MEAL_DETAIL_QUERY_PARAM).test()
        testObserver.addTo(compositeDisposable)
        val actualMealDetail = testObserver.values()!![0]


        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        Truth.assertThat(actualMealDetail).isEqualTo(expectedMealDetailModel)
    }


}