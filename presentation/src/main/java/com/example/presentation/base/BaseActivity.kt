package com.example.presentation.base

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {
    abstract val viewModel: ViewModel
}