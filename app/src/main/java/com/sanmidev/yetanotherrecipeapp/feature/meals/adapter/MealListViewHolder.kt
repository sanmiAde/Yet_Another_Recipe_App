package com.sanmidev.yetanotherrecipeapp.feature.meals.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealModel
import com.sanmidev.yetanotherrecipeapp.databinding.MealListItemBinding
import com.sanmidev.yetanotherrecipeapp.utils.GlideApp

class MealListViewHolder(private val binding: MealListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mealModel: MealModel) {
        with(binding.imgMeal) {
            GlideApp.with(this.context)
                .load(mealModel.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        binding.txtMealName.text = mealModel.name
    }
}