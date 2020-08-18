package com.sanmidev.yetanotherrecipeapp.feature.categories.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryModel
import com.sanmidev.yetanotherrecipeapp.databinding.CategoryListItemBinding
import com.sanmidev.yetanotherrecipeapp.utils.GlideApp

class CategoryViewHolder(private val categoryListItemBinding: CategoryListItemBinding) :
    RecyclerView.ViewHolder(categoryListItemBinding.root) {

    fun bind(categoryModel: CategoryModel) {
        with(categoryListItemBinding.imgCategory) {
            GlideApp.with(this.context)
                .load(categoryModel.image)
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        categoryListItemBinding.txtCategoryName.text = categoryModel.category

    }

}