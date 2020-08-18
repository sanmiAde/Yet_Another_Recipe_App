package com.sanmidev.yetanotherrecipeapp.feature.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sanmidev.yetanotherrecipeapp.databinding.FragmentCategoryDescriptionBinding


class CategoryDescriptionFragment : BottomSheetDialogFragment() {

    private var fragmentCategoryDescriptionBinding: FragmentCategoryDescriptionBinding? = null

    private val navArgs by navArgs<CategoryDescriptionFragmentArgs>()

    private val binding: FragmentCategoryDescriptionBinding
        get() = fragmentCategoryDescriptionBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentCategoryDescriptionBinding =
            FragmentCategoryDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtCategoryDesc.text = navArgs.description
    }

    override fun onDestroyView() {
        fragmentCategoryDescriptionBinding = null
        super.onDestroyView()
    }
}