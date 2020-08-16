package com.sanmidev.yetanotherrecipeapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.YetAnotherRecipeApplication
import com.sanmidev.yetanotherrecipeapp.di.components.ApplicationComponent

class MainActivity : AppCompatActivity() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationComponent = (application as YetAnotherRecipeApplication).appComponent
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}