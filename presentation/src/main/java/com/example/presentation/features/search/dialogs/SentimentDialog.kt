package com.example.presentation.features.search.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.presentation.R
import com.example.presentation.features.search.enums.PSentiment
import com.example.presentation.general.extensions.tint
import kotlinx.android.synthetic.main.dialog_loading.*
import kotlinx.android.synthetic.main.sentiment_dialog.*


class SentimentDialog(
    pSentiment: PSentiment,
    context: Context
) {

    private val dialog = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.sentiment_dialog)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressLoading.tint(R.color.colorPrimary)
        imgDialogClose.setOnClickListener { dismiss() }
        imgDialogEmoji.setImageResource(pSentiment.emojiResId)
        txtDialogDesc.setText(pSentiment.message)
        constraintSentimentContainer.setBackgroundResource(pSentiment.backgroundColorResId)
    }

    fun show() {
        with(dialog) {
            if (!isShowing) {
                show()
            }
        }
    }
}