package com.sanmidev.yetanotherrecipeapp.di.modules

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
class AssistedInjectModule