package com.example.presentation.di

import com.example.domain.twitter.TwitterUseCase
import com.example.presentation.features.search.SearchUserActivity
import com.example.presentation.features.search.SearchUserViewModel
import com.example.presentation.features.search.mappers.PTweetMapper
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val presentationModule = applicationContext {

    bean {
        PTweetMapper()
    }

    // ===== SEARCH USER

    factory { SearchUserActivity() }

    viewModel {
        SearchUserViewModel(
            get<TwitterUseCase>(),
            get<PTweetMapper>()
        )
    }
}