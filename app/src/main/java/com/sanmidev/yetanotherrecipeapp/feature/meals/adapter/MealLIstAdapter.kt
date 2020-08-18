package com.sanmidev.yetanotherrecipeapp.feature.meals.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealModel
import com.sanmidev.yetanotherrecipeapp.databinding.MealListItemBinding

typealias OnMealImageClickCallback = (String) -> Unit

class MealLIstAdapter(
    private val layoutInflater: LayoutInflater,
    private val onImageOnClickCallback: OnMealImageClickCallback
) : ListAdapter<MealModel, MealListViewHolder>(MealListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val binding = MealListItemBinding.inflate(layoutInflater, parent, false)

        val viewHolder = MealListViewHolder(binding)

        binding.root.setOnClickListener {
            onImageOnClickCallback.invoke(getItem(viewHolder.adapterPosition).id)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        val mealModel = getItem(position)

        holder.bind(mealModel)
    }

    override fun submitList(list: MutableList<MealModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}