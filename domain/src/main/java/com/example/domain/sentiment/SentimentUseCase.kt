package com.example.domain.sentiment

import com.example.domain.sentiment.enums.Sentiment
import io.reactivex.Single

interface SentimentUseCase {
    fun analyzeTweet(
        tweet: String
    ): Single<Sentiment>
}