package com.example.presentation.general.extensions

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation


fun View.fadeOut() {
    if (this.visibility == View.VISIBLE) {
        animation = AlphaAnimation(1f, 0.3f).apply {
            interpolator = AccelerateInterpolator()
            startOffset = 0
            duration = 400
        }
        visibility = View.GONE
    }
}
