package com.sanmidev.yetanotherrecipeapp.feature.categories

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.data.local.model.CategoryListModel
import com.sanmidev.yetanotherrecipeapp.databinding.FragmentCategoriesBinding
import com.sanmidev.yetanotherrecipeapp.feature.MainActivity
import com.sanmidev.yetanotherrecipeapp.feature.categories.adapter.CategoryListAdapter
import com.sanmidev.yetanotherrecipeapp.utils.*
import io.cabriole.decorator.ColumnProvider
import io.cabriole.decorator.GridMarginDecoration
import timber.log.Timber
import javax.inject.Inject


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private var fragmentCategoriesBinding: FragmentCategoriesBinding? = null

    private val binding: FragmentCategoriesBinding
        get() = fragmentCategoriesBinding!!

    private lateinit var categoryAdapter: CategoryListAdapter

    @Inject
    lateinit var vmFactory: CategoriesViewModel.VmFactory.Factory

    private val viewModel by viewModels<CategoriesViewModel>
    { vmFactory.createFactory(this) }

    private val navController by lazy { findNavController() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentCategoriesBinding = FragmentCategoriesBinding.bind(view)

        binding.layoutToolbar.toolbar.title = getString(R.string.categories_txt)

        initViews()
        observeGetCategoriesLiveData()
    }

    private fun initViews() {
        //Adapter
        categoryAdapter = CategoryListAdapter(this.layoutInflater,
            {
                Timber.d(it.toString())
            }, { description: String ->
                val directions =
                    CategoriesFragmentDirections.actionCategoriesFragmentToCategoryDescriptionFragment(
                        description
                    )
                navController.navigate(directions)
            })

        //Recyclerview
        binding.rvCategory.apply {

            val spanCount = context.resources.getInteger(R.integer.recycler_view_span_count)
            val margin = context.resources.dpToPx(8)

            this.layoutManager = GridLayoutManager(requireContext(), spanCount)
            this.addItemDecoration(GridMarginDecoration(margin, object : ColumnProvider {
                override fun getNumberOfColumns(): Int = spanCount
            }, orientation = RecyclerView.VERTICAL))

            this.setHasFixedSize(true)
            this.adapter = categoryAdapter
        }
    }

    private fun observeGetCategoriesLiveData() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { result: Result<CategoryListModel> ->
            binding.rvCategory.showIf { result is Result.Success }
            binding.shimmerFrameLayout.showShimmerIf { result is Result.InProgress }

            when (result) {
                is Result.Success -> {
                    categoryAdapter.submitList(result.data.categories.toMutableList())
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
        binding.rvCategory.adapter = null
        super.onDestroyView()
    }
}