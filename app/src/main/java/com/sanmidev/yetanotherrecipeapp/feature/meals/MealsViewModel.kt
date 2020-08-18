package com.sanmidev.yetanotherrecipeapp.feature.meals

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealListModel
import com.sanmidev.yetanotherrecipeapp.data.remote.repo.MealsDBRepository
import com.sanmidev.yetanotherrecipeapp.utils.AppScheduler
import com.sanmidev.yetanotherrecipeapp.utils.Result
import com.sanmidev.yetanotherrecipeapp.utils.applySchedulers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MealsViewModel(
    private val mealsDBRepository: MealsDBRepository,
    private val appScheduler: AppScheduler,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val mealListMutableLiveData = MutableLiveData<Result<MealListModel>>()

    val mealListLiveData: LiveData<Result<MealListModel>>
        get() = mealListMutableLiveData

    private var savedMeals: MealListModel?
        set(value) {
            savedStateHandle.set(MEALS_LIST_KEY, value)
        }
        get() = savedStateHandle.get(MEALS_LIST_KEY)


    init {
        //Upon initialisation, get the meal list from the saved instance state.
        //IF a meal list is found, update the mealListMutableLiveData with Result.Success.
        //If not, get the meals from the API and save it
        savedMeals?.let {
            mealListMutableLiveData.value = Result.Success(it)
        } ?: getMeals()

    }


    private fun getMeals() {
        val mealName = requireNotNull(savedStateHandle.get<String>(MEAL_LIST_NAME))

        mealsDBRepository.getMeals(mealName)
            .applySchedulers(appScheduler)
            .doOnSubscribe {
                mealListMutableLiveData.value = Result.InProgress
            }.subscribeBy(onSuccess = {
                //saved meal list to saved state handle. This is done because of process death.
                //During process death, this data would be lost.
                savedMeals = it
                mealListMutableLiveData.value = Result.Success(it)

            }, onError = {
                mealListMutableLiveData.value =
                    Result.Error.NonRecoverableError(R.string.meals_list_error_txt)
            }).addTo(compositeDisposable)
    }

    fun refresh() {
        getMeals()
    }


    companion object {
        const val MEALS_LIST_KEY = "com.sanmidev.yetanotherrecipeapp.meals_list_key"
        const val MEAL_LIST_NAME = "com.sanmidev.yetanotherrecipeapp.meals_list_name"

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
            return MealsViewModel(
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