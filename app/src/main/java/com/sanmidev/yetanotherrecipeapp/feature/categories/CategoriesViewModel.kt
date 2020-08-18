package com.sanmidev.yetanotherrecipeapp.feature.categories

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.data.remote.repo.MealsDBRepository
import com.sanmidev.yetanotherrecipeapp.utils.AppScheduler
import com.sanmidev.yetanotherrecipeapp.utils.Result
import com.sanmidev.yetanotherrecipeapp.utils.applySchedulers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class CategoriesViewModel(
    private val mealsDBRepository: MealsDBRepository,
    private val appScheduler: AppScheduler,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val categoriesMutableLiveData = MutableLiveData<Result<CategoryListModel>>()

    private var categoriesData: CategoryListModel?
        set(value) {
            savedStateHandle.set(CATEGORIES_LIST_KEY, value)
        }
        get() {
            return savedStateHandle.get<CategoryListModel>(CATEGORIES_LIST_KEY)
        }

    val categoriesLiveData: LiveData<Result<CategoryListModel>>
        get() = categoriesMutableLiveData

    init {

        //Upon initialisation get the category list data from the [SavedStateHandle].
        // If a category list has been saved, return it as [LiveData].
        // if not make an api request to get the list of categories.
        categoriesData?.let {
            categoriesMutableLiveData.value = Result.Success(it)
        } ?: getCategories()
    }

    private fun getCategories() {

        mealsDBRepository.getCategories()
            .applySchedulers(appScheduler)
            .doOnSubscribe {
                categoriesMutableLiveData.value = Result.InProgress
            }
            .subscribeBy(onSuccess = { categoryListModel: CategoryListModel ->
                categoriesMutableLiveData.value = Result.Success(categoryListModel)
                categoriesData = categoryListModel
            }, onError = {
                categoriesMutableLiveData.value =
                    Result.Error.NonRecoverableError(R.string.categories_error_txt)
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    companion object {
        const val CATEGORIES_LIST_KEY = "com.sanmidev.yetanotherrecipeapp.categories_list_key"
    }

    class VmFactory @AssistedInject constructor(
        private val mealsDBRepository: MealsDBRepository,
        private val appScheduler: AppScheduler,
        @Assisted savedStateRegistryOwner: SavedStateRegistryOwner,
        @Assisted defaultArgs: Bundle
    ) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, defaultArgs) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return CategoriesViewModel(
                mealsDBRepository,
                appScheduler,
                handle
            ) as T
        }


        @AssistedInject.Factory
        interface Factory {
            fun createFactory(
                savedStateRegistryOwner: SavedStateRegistryOwner,
                defaultArgs: Bundle = Bundle.EMPTY
            ): VmFactory
        }
    }

}