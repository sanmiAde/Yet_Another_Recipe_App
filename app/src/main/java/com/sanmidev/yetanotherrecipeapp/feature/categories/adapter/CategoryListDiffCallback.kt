package com.sanmidev.yetanotherrecipeapp.feature.categories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryModel


class CategoryListDiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
    override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem == newItem
    }

}
