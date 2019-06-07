package com.example.data.sentiment

import com.example.data.sentiment.ro.DSentimentAnalyzerRequestObject
import com.example.data.sentiment.ro.DSentimentAnalyzerResponseObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SentimentRemoteSource {

    @POST("./documents:analyzeSentiment")
    fun analyzeTweet(
        @Query("key") key: String,
        @Body request: DSentimentAnalyzerRequestObject
    ): Single<DSentimentAnalyzerResponseObject>
}