package com.example.presentation.features.search.enums

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.example.presentation.R

enum class PSentiment(
    @ColorRes val backgroundColorResId: Int,
    @DrawableRes val emojiResId: Int,
    @StringRes val message: Int
) {
    HAPPY(
        R.color.yellow_dialog,
        R.drawable.happy_emoji,
        R.string.emoji_happy
    ),
    NORMAL(
        R.color.gray_dialog,
        R.drawable.normal_emoji,
        R.string.emoji_normal
    ),
    SAD(
        R.color.blue_dialog,
        R.drawable.sad_emoji,
        R.string.emoji_sad
    )
}