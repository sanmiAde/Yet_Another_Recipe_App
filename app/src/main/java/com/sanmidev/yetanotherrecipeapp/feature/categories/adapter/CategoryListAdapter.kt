package com.sanmidev.yetanotherrecipeapp.feature.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryModel
import com.sanmidev.yetanotherrecipeapp.databinding.CategoryListItemBinding

typealias CategoryItemOnClickCallback = (CategoryModel) -> Unit

class CategoryListAdapter(
    private val layoutInflater: LayoutInflater,
    private val callback: CategoryItemOnClickCallback
) : ListAdapter<CategoryModel, CategoryViewHolder>(
    CategoryListDiffCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryListItemBinding.inflate(layoutInflater, parent, false)

        val viewholder = CategoryViewHolder(
            binding
        )
        binding.root.setOnClickListener {
            callback.invoke(getItem(viewholder.adapterPosition))
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