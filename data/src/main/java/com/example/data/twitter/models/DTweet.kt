package com.example.data.twitter.models

import com.google.gson.annotations.SerializedName


data class DTweet(
    @SerializedName("text") val tweet: String?,
    @SerializedName("user") val user: DUser?
)