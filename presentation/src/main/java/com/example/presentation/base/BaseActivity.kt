package com.example.presentation.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.presentation.R
import com.example.presentation.general.dialogs.LoadingDialog

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {
    abstract val viewModel: ViewModel
    private val loadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prepareObservers()
    }

    private fun prepareObservers() {
        viewModel
            .isLoadingGeneral()
            .observe(this, Observer<Boolean> { isLoadingN ->
                isLoadingN?.let { isLoading ->
                    when {
                        isLoading -> showLoading()
                        else -> hideLoading()
                    }
                }
            })

        viewModel
            .mustShowErrorDialog()
            .observe(this, Observer<Int> { isLoadingN ->
                isLoadingN?.let { errorMessage ->
                    AlertDialog.Builder(this)
                        .setTitle(getString(R.string.title_error))
                        .setMessage(getString(errorMessage))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                            dialog.dismiss()
                        }.show()
                }
            })

    }

    private fun showLoading() {
        loadingDialog.show()
    }

    private fun hideLoading() {
        loadingDialog.hide()
    }
}