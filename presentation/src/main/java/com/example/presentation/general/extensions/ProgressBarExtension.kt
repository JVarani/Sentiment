package com.example.presentation.general.extensions

import android.graphics.PorterDuff
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.widget.ProgressBar

fun ProgressBar.tint(@ColorRes color: Int) {
    indeterminateDrawable?.setColorFilter(
        ContextCompat.getColor(context, color),
        PorterDuff.Mode.SRC_IN
    )
}