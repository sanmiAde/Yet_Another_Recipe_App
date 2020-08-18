package com.sanmidev.yetanotherrecipeapp.feature.mealDetail


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.data.local.model.mealDetail.MealDetailModel
import com.sanmidev.yetanotherrecipeapp.databinding.FragmentMealDetailBinding
import com.sanmidev.yetanotherrecipeapp.feature.MainActivity
import com.sanmidev.yetanotherrecipeapp.utils.*
import javax.inject.Inject


class MealDetailFragment : Fragment(R.layout.fragment_meal_detail) {

    private var fragmentMealDetailBinding: FragmentMealDetailBinding? = null

    val binding: FragmentMealDetailBinding
        get() = fragmentMealDetailBinding!!

    private val args by navArgs<MealDetailFragmentArgs>()

    @Inject
    lateinit var vmFactory: MealDetailViewModel.VmFactory.Factory

    private val viewModel by viewModels<MealDetailViewModel> {
        val bundle = bundleOf(Pair(MealDetailViewModel.MEAL_ID_KEY, args.id))
        vmFactory.createFactory(this, bundle)
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMealDetailBinding = FragmentMealDetailBinding.bind(view)

        with(binding.imageMeal) {
            GlideApp.with(this)
                .load(args.imageLink)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        binding.toolbar.initToolbarBackButton(requireActivity())
        observeGetMealDetailLiveData()
    }

    private fun observeGetMealDetailLiveData() {
        viewModel.mealDetailLiveData.observe(viewLifecycleOwner) { result: Result<MealDetailModel> ->

            binding.textDesc.hideIf { result is Result.InProgress }
            binding.shimmerLayout.showShimmerIf { result is Result.InProgress }

            when (result) {
                is Result.Success -> {
                    binding.toolbar.title = result.data.name
                    binding.textDesc.text = result.data.instruction
                }
                is Result.Error.RecoverableError -> {
                    fireToast(requireContext(), requireContext().getString(result.exceptionId))
                }
                is Result.Error.NonRecoverableError -> {
                    fireToast(requireContext(), requireContext().getString(result.exceptionId))
                }
                Result.InProgress -> {
                }
            }
        }
    }


    override fun onDestroyView() {
        fragmentMealDetailBinding = null
        super.onDestroyView()

    }
}