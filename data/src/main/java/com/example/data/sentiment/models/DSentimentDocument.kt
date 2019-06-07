package com.example.data.sentiment.models

import com.google.gson.annotations.SerializedName

data class DSentimentDocument(
    @SerializedName("content") val content: String,
    @SerializedName("type") val type: String = "PLAIN_TEXT"
)