package com.example.presentation.di

import com.example.presentation.features.search.SearchUserViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val presentationModule = applicationContext {

    // ===== SEARCH USER
    viewModel {
        SearchUserViewModel()
    }
}