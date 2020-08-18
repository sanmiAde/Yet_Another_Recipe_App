package com.sanmidev.yetanotherrecipeapp.data.local.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealListModel(
    val meals: List<MealModel>
) : Parcelable