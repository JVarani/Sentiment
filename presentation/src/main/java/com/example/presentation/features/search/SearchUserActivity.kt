package com.example.presentation.features.search

import android.os.Bundle
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import org.koin.android.architecture.ext.viewModel

class SearchUserActivity : BaseActivity<SearchUserViewModel>() {
    override val viewModel by viewModel<SearchUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
    }
}