package com.example.presentation.general.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.presentation.R
import com.example.presentation.general.extensions.tint
import kotlinx.android.synthetic.main.dialog_loading.*

class LoadingDialog(
    context: Context
) {
    private val dialog = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressLoading.tint(R.color.colorPrimary)
    }

    fun show() {
        with(dialog) {
            if (!isShowing) {
                show()
            }
        }
    }

    fun show(customMessage: String) {
        with(dialog) {
            if (!isShowing) {
                txtProgressMessage.text = customMessage
                show()
            }
        }
    }

    fun hide() {
        if (dialog.isShowing)
            dialog.dismiss()
    }

    fun isShowing(): Boolean {
        return dialog.isShowing
    }
}