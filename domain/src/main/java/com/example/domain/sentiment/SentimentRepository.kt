package com.example.domain.sentiment

import com.example.domain.sentiment.models.SentimentScore
import io.reactivex.Single

interface SentimentRepository {

    fun analyzeTweet(
        tweet: String
    ): Single<SentimentScore>
}