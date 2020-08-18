package com.sanmidev.yetanotherrecipeapp.feature.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.jraska.livedata.test
import com.sanmidev.yetanotherrecipeapp.DataUtils
import com.sanmidev.yetanotherrecipeapp.NetworkUtils
import com.sanmidev.yetanotherrecipeapp.RepositoryUtils
import com.sanmidev.yetanotherrecipeapp.utils.Result
import com.sanmidev.yetanotherrecipeapp.utils.TestSchedulers
import io.reactivex.disposables.CompositeDisposable
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection

class CategoriesViewModelTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var CUT: CategoriesViewModel

    private val compositeDisposable = CompositeDisposable()

    private val repo = RepositoryUtils.provideMealsDbRepostory(mockWebServer)
    private val testSchedulers = TestSchedulers()
    private val savedStateHandle = SavedStateHandle()
    private val dispacher = NetworkUtils.getMockWebserverDispatcher()

    @Before
    fun setUp() {

        CUT = CategoriesViewModel(repo, testSchedulers, savedStateHandle)
    }

    @After
    fun tearDown() {
        compositeDisposable.clear()
    }

    @Test
    fun getCategoriesLiveData_shouldReturnResultSuccessWhenRequestIsSuccessful() {
        //GIVEN
        mockWebServer.dispatcher = dispacher
        CUT = CategoriesViewModel(repo, testSchedulers, savedStateHandle)

        //WHEN
        val testObserver = CUT.categoriesLiveData.test()
        val value = testObserver.value()
        val expectedCategoryList = DataUtils.categoriesData.second

        //THEN
        Truth.assertThat(value).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((value as Result.Success).data.categories)
            .containsExactlyElementsIn(expectedCategoryList.categories)
    }

    @Test
    fun getCategoriesLiveData_shouldReturnResultErrorWhenRequestIsFails() {
        //GIVEN
        mockWebServer.enqueue(
            MockResponse().setBody(
                "Content Not Found"
            ).setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        )
        CUT = CategoriesViewModel(repo, testSchedulers, savedStateHandle)

        //WHEN
        val testObserver = CUT.categoriesLiveData.test()
        val value = testObserver.value()

        //THEN
        Truth.assertThat(value).isInstanceOf(Result.Error.NonRecoverableError::class.java)

    }
}