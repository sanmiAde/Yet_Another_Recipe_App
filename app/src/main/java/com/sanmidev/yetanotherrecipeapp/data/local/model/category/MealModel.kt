package com.sanmidev.yetanotherrecipeapp.data.local.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MealModel(
    val id: String,
    val name: String,
    val image: String
) : Parcelable