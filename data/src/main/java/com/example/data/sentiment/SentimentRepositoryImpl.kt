package com.example.data.sentiment

import com.example.data.BuildConfig
import com.example.data.sentiment.mappers.DSentimentAnalyzerResponseObjectMapper
import com.example.data.sentiment.mappers.SentimentErrorMapper
import com.example.data.sentiment.models.DSentimentDocument
import com.example.data.sentiment.ro.DSentimentAnalyzerRequestObject
import com.example.domain.sentiment.SentimentRepository
import com.example.domain.sentiment.models.SentimentScore
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SentimentRepositoryImpl(
    private val sentimentRemoteSource: SentimentRemoteSource,
    private val dSentimentAnalyzerResponseObjectMapper: DSentimentAnalyzerResponseObjectMapper,
    private val sentimentErrorMapper: SentimentErrorMapper
) : SentimentRepository {

    override fun analyzeTweet(tweet: String): Single<SentimentScore> {
        return sentimentRemoteSource
            .analyzeTweet(
                key = BuildConfig.GOOGLE_LANGUAGE_KEY,
                request = DSentimentAnalyzerRequestObject(
                    document = DSentimentDocument(
                        content = tweet
                    )
                )
            )
            .subscribeOn(Schedulers.newThread())
            .map(dSentimentAnalyzerResponseObjectMapper::transform)
            .onErrorResumeNext { error ->
                Single.error(sentimentErrorMapper.transform(error))
            }
    }
}