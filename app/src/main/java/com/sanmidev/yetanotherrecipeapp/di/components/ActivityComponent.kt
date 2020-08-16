package com.sanmidev.yetanotherrecipeapp.di.components

import com.sanmidev.yetanotherrecipeapp.di.scopes.PerActivityScope
import dagger.Subcomponent

@PerActivityScope
@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}