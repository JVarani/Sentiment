package com.example.data.sentiment.ro

import com.example.data.sentiment.models.DSentimentScore
import com.google.gson.annotations.SerializedName

data class DSentimentAnalyzerResponseObject(
    @SerializedName("documentSentiment") val dSentimentScore: DSentimentScore
)