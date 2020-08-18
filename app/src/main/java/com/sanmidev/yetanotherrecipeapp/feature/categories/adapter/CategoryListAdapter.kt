package com.sanmidev.yetanotherrecipeapp.feature.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sanmidev.yetanotherrecipeapp.data.local.model.categoryList.CategoryModel
import com.sanmidev.yetanotherrecipeapp.databinding.CategoryListItemBinding

typealias CategoryImageOnClickCallback = (CategoryModel) -> Unit
typealias CategoryDescIconOnClickCallback = (String) -> Unit

class CategoryListAdapter(
    private val layoutInflater: LayoutInflater,
    private val onImageClickcallback: CategoryImageOnClickCallback,
    private val onDescIconClickCallback: CategoryDescIconOnClickCallback
) : ListAdapter<CategoryModel, CategoryViewHolder>(
    CategoryListDiffCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryListItemBinding.inflate(layoutInflater, parent, false)

        val viewholder = CategoryViewHolder(
            binding
        )
        binding.imgCategory.setOnClickListener {
            onImageClickcallback.invoke(getItem(viewholder.adapterPosition))
        }

        binding.imgDesc.setOnClickListener {
            onDescIconClickCallback.invoke(getItem(viewholder.adapterPosition).description)
        }

        return viewholder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryModel = getItem(position)
        holder.bind(categoryModel)
    }

    override fun submitList(list: MutableList<CategoryModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}