package com.example.domain.sentiment

import com.example.domain.sentiment.enums.Sentiment
import com.example.domain.sentiment.models.SentimentScore
import io.reactivex.Scheduler
import io.reactivex.Single

class SentimentUseCasesImpl(
    private val scheduler: Scheduler,
    private val repository: SentimentRepository
) : SentimentUseCase {
    override fun analyzeTweet(tweet: String): Single<Sentiment> {
        return repository
            .analyzeTweet(tweet)
            .subscribeOn(scheduler)
            .map {
                map(it)
            }
    }

    private fun map(entity: SentimentScore): Sentiment {
        return when {
            entity.score >= 0.5 -> Sentiment.HAPPY
            entity.score < 0.5 -> Sentiment.NORMAL
            else -> Sentiment.SAD
        }
    }
}