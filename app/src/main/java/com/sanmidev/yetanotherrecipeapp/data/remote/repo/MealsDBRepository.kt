package com.sanmidev.yetanotherrecipeapp.data.remote.repo

import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryListModel
import io.reactivex.Single

interface MealsDBRepository {
    fun getCategories(): Single<CategoryListModel>
}