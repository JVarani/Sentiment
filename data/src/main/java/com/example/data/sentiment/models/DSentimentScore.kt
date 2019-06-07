package com.example.data.sentiment.models

import com.google.gson.annotations.SerializedName

data class DSentimentScore(
    @SerializedName("score") val score: Double
)