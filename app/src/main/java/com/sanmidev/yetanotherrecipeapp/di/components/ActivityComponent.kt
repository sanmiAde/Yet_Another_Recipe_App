package com.sanmidev.yetanotherrecipeapp.di.components

import com.sanmidev.yetanotherrecipeapp.di.scopes.PerActivityScope
import com.sanmidev.yetanotherrecipeapp.feature.categories.CategoriesFragment
import dagger.Subcomponent

@PerActivityScope
@Subcomponent
interface ActivityComponent {

    fun inject(categoriesFragment: CategoriesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}