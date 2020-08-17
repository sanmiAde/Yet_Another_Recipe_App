package com.sanmidev.yetanotherrecipeapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanmidev.yetanotherrecipeapp.R
import com.sanmidev.yetanotherrecipeapp.YetAnotherRecipeApplication
import com.sanmidev.yetanotherrecipeapp.di.components.ActivityComponent

class MainActivity : AppCompatActivity() {
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent =
            (application as YetAnotherRecipeApplication).appComponent.activityComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}