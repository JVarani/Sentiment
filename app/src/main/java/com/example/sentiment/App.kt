package com.example.sentiment

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.presentation.di.presentationModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        startKoin(
            this,
            listOf(
                dataModule,
                domainModule,
                presentationModule
            )
        )
    }
}