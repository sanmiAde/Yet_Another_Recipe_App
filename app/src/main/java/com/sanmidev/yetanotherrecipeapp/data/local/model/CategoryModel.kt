package com.sanmidev.yetanotherrecipeapp.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(
    val id: String,
    val category: String,
    val description: String,
    val image: String
) : Parcelable