package com.sanmidev.yetanotherrecipeapp.feature.mealDetail

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.data.local.model.mealDetail.MealDetailModel
import com.sanmidev.yetanotherrecipeapp.data.remote.repo.MealsDBRepository
import com.sanmidev.yetanotherrecipeapp.utils.AppScheduler
import com.sanmidev.yetanotherrecipeapp.utils.Result
import com.sanmidev.yetanotherrecipeapp.utils.applySchedulers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MealDetailViewModel(
    private val mealsDBRepository: MealsDBRepository,
    private val appScheduler: AppScheduler,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val mealDetailMutableLiveData = MutableLiveData<Result<MealDetailModel>>()

    val mealDetailLiveData
        get() = mealDetailMutableLiveData

    private var mealDetailSavedState: MealDetailModel?
        set(value) {
            savedStateHandle.set(MEAL_DETAIL_KEY, value)
        }
        get() {
            return savedStateHandle.get(MEAL_DETAIL_KEY)
        }

    init {
        mealDetailSavedState?.let {
            mealDetailMutableLiveData.value = Result.Success(it)
        } ?: getMealDetail()
    }

    private fun getMealDetail() {
        val id = requireNotNull(savedStateHandle.get<String>(MEAL_ID_KEY))
        mealsDBRepository.getMealDetail(id)
            .applySchedulers(appScheduler)
            .doOnSubscribe {
                mealDetailMutableLiveData.value = Result.InProgress
            }.subscribeBy(onError = {
                mealDetailMutableLiveData.value =
                    Result.Error.NonRecoverableError(R.string.no_meal_detail_error)
            }, onSuccess = {
                mealDetailSavedState = it
                mealDetailMutableLiveData.value = Result.Success(it)
            }).addTo(compositeDisposable)
    }


    companion object {
        const val MEAL_DETAIL_KEY = "com.sanmidev.yetanotherrecipeapp.meal_detail_key"
        const val MEAL_ID_KEY = "com.sanmidev.yetanotherrecipeapp.meal_id_key"
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
            return MealDetailViewModel(
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