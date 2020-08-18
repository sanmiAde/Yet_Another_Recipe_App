package com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryListModel(
    val categories: List<CategoryModel>
) : Parcelable
