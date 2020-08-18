package com.sanmidev.yetanotherrecipeapp.data.local.model.mealDetail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealDetailModel(
    val id: String,
    val name: String,
    val image: String,
    val instruction: String,
    val youtubeLink: String
) : Parcelable