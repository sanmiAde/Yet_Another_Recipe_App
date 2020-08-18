package com.sanmidev.yetanotherrecipeapp.feature.meals

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.data.local.model.category.MealListModel
import com.sanmidev.yetanotherrecipeapp.databinding.FragmentMealsBinding
import com.sanmidev.yetanotherrecipeapp.feature.MainActivity
import com.sanmidev.yetanotherrecipeapp.feature.meals.adapter.MealLIstAdapter
import com.sanmidev.yetanotherrecipeapp.utils.*
import io.cabriole.decorator.ColumnProvider
import io.cabriole.decorator.GridMarginDecoration
import javax.inject.Inject


class MealsFragment : Fragment(R.layout.fragment_meals) {

    private var fragmentMealsBinding: FragmentMealsBinding? = null

    private val binding: FragmentMealsBinding
        get() = fragmentMealsBinding!!

    private lateinit var mealsAdapter: MealLIstAdapter

    private val args by navArgs<MealsFragmentArgs>()

    @Inject
    lateinit var vmFactory: MealsViewModel.VmFactory.Factory

    private val viewModel by viewModels<MealsViewModel> {
        val bundle = bundleOf(Pair(MealsViewModel.MEAL_LIST_NAME, args.name))
        vmFactory.createFactory(this, bundle)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMealsBinding = FragmentMealsBinding.bind(view)


        initViews()
        observeGetMealLiveData()
    }

    private fun observeGetMealLiveData() {
        viewModel.mealListLiveData.observe(viewLifecycleOwner) { result: Result<MealListModel> ->

            binding.recyclerView.hideIf { result is Result.InProgress }
            binding.shimmer.shimmer.showShimmerIf { result is Result.InProgress }

            when (result) {
                is Result.Success -> {
                    mealsAdapter.submitList(result.data.meals.toMutableList())
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

    private fun initViews() {
        //toolbar
        binding.layoutToolbar.toolbar.title = args.name
        binding.layoutToolbar.toolbar.initToolbarBackButton(requireActivity())

        //adapter
        mealsAdapter = MealLIstAdapter(this.layoutInflater) {

        }

        //Recyclerview
        binding.recyclerView.apply {

            val spanCount = context.resources.getInteger(R.integer.recycler_view_span_count)
            val margin = context.resources.dpToPx(8)

            this.layoutManager = GridLayoutManager(requireContext(), spanCount)
            this.addItemDecoration(GridMarginDecoration(margin, object : ColumnProvider {
                override fun getNumberOfColumns(): Int = spanCount
            }, orientation = RecyclerView.VERTICAL))

            this.setHasFixedSize(true)
            this.adapter = mealsAdapter
        }

        //swipe to refresh
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        fragmentMealsBinding = null
        super.onDestroyView()
    }
}