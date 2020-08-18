package com.sanmidev.yetanotherrecipeapp.di.components

import com.sanmidev.yetanotherrecipeapp.di.scopes.PerActivityScope
import com.sanmidev.yetanotherrecipeapp.feature.categories.CategoriesFragment
import com.sanmidev.yetanotherrecipeapp.feature.mealDetail.MealDetailFragment
import com.sanmidev.yetanotherrecipeapp.feature.meals.MealsFragment
import dagger.Subcomponent

@PerActivityScope
@Subcomponent
interface ActivityComponent {

    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(mealsFragment: MealsFragment)
    fun inject(mealDetailFragment: MealDetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}