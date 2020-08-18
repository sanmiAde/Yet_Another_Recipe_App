package com.sanmidev.yetanotherrecipeapp.feature.meals


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.jraska.livedata.test
import com.sanmidev.yetanotherrecipeapp.NetworkUtils
import com.sanmidev.yetanotherrecipeapp.RepositoryUtils
import com.sanmidev.yetanotherrecipeapp.utils.Result
import com.sanmidev.yetanotherrecipeapp.utils.TestSchedulers
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealsViewModelTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var CUT: MealsViewModel

    private val repo = RepositoryUtils.provideMealsDbRepostory(mockWebServer)
    private val testSchedulers = TestSchedulers()

    @Before
    fun setUp() {

    }

    @Test
    fun getMealsList_shouldReturnListOfMeal_RequestIsSuccessful() {
        //GIVEN
        mockWebServer.dispatcher = NetworkUtils.getMealsMockWebserverDispatcher()

        val savedStateHandle = SavedStateHandle()
        savedStateHandle.set(MealsViewModel.MEAL_LIST_NAME, NetworkUtils.MEAL_PATH_QUERY_PARAM)
        CUT = MealsViewModel(repo, testSchedulers, savedStateHandle)

        //WHEN
        val testObserver = CUT.mealListLiveData.test()
        val result = testObserver.value()

        //THEN
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun getMealsList_shouldReturnResultError_RequestFails() {
        //GIVEN
        mockWebServer.dispatcher = NetworkUtils.getMealsMockWebserverDispatcher()

        val savedStateHandle = SavedStateHandle()
        savedStateHandle.set(MealsViewModel.MEAL_LIST_NAME, "Wrong")
        CUT = MealsViewModel(repo, testSchedulers, savedStateHandle)

        //WHEN
        val testObserver = CUT.mealListLiveData.test()
        val result = testObserver.value()

        //THEN
        Truth.assertThat(result).isInstanceOf(Result.Error.NonRecoverableError::class.java)
    }


    @After
    fun tearDown() {
    }
}