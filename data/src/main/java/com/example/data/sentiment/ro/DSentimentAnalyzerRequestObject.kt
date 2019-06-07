package com.example.data.sentiment.ro

import com.example.data.sentiment.models.DSentimentDocument
import com.google.gson.annotations.SerializedName

data class DSentimentAnalyzerRequestObject(
    @SerializedName("document") val document: DSentimentDocument,
    @SerializedName("encodingType") val encodingType: String = "UTF8"
)