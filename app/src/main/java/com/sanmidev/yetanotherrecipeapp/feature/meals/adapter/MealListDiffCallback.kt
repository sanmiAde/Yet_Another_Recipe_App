package com.sanmidev.yetanotherrecipeapp.feature.meals.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealModel

class MealListDiffCallback : DiffUtil.ItemCallback<MealModel>() {
    override fun areItemsTheSame(oldItem: MealModel, newItem: MealModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MealModel, newItem: MealModel): Boolean {
        return oldItem == newItem
    }
}