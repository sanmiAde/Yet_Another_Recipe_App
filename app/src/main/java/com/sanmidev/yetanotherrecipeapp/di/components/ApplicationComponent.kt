package com.sanmidev.yetanotherrecipeapp.di.components

import android.app.Application
import com.sanmidev.yetanotherrecipeapp.di.modules.ApplicationModule
import com.sanmidev.yetanotherrecipeapp.di.modules.AssistedInjectModule
import com.sanmidev.yetanotherrecipeapp.di.modules.NetworkModule
import com.sanmidev.yetanotherrecipeapp.di.scopes.PerApplicationScope
import dagger.BindsInstance
import dagger.Component

@PerApplicationScope
@Component(modules = [NetworkModule::class, ApplicationModule::class, AssistedInjectModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun activityComponent(): ActivityComponent.Factory
}