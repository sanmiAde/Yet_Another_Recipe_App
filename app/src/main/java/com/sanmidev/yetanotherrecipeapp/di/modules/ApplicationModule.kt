package com.sanmidev.yetanotherrecipeapp.di.modules

import com.sanmidev.yetanotherrecipeapp.data.remote.repo.MeallDBRepositoryImpl
import com.sanmidev.yetanotherrecipeapp.data.remote.repo.MealsDBRepository
import com.sanmidev.yetanotherrecipeapp.di.scopes.PerApplicationScope
import com.sanmidev.yetanotherrecipeapp.utils.AppScheduler
import com.sanmidev.yetanotherrecipeapp.utils.RxSchedulers
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    @PerApplicationScope
    abstract fun bindsMealsDBRepository(meallDBRepositoryImpl: MeallDBRepositoryImpl): MealsDBRepository

    @Binds
    @PerApplicationScope
    abstract fun bindsAppScheduler(rxSchedulers: RxSchedulers): AppScheduler
}