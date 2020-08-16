package com.sanmidev.yetanotherrecipeapp

import android.app.Application
import com.sanmidev.yetanotherrecipeapp.di.components.DaggerApplicationComponent
import timber.log.Timber

class YetAnotherRecipeApplication : Application() {

    val appComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}