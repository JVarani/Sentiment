package com.example.domain.di

import com.example.domain.sentiment.SentimentRepository
import com.example.domain.sentiment.SentimentUseCase
import com.example.domain.sentiment.SentimentUseCasesImpl
import com.example.domain.twitter.TwitterRepository
import com.example.domain.twitter.TwitterUseCase
import com.example.domain.twitter.TwitterUseCasesImpl
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.applicationContext

const val SCHEDULER_NEW_THREAD = "schedulerNewThread"

val domainModule = applicationContext {

    bean(SCHEDULER_NEW_THREAD) {
        Schedulers.newThread()
    }

    // ========== TWITTER

    factory {
        TwitterUseCasesImpl(
            get(SCHEDULER_NEW_THREAD),
            get<TwitterRepository>()
        ) as TwitterUseCase
    }

    // ========== SENTIMENT

    factory {
        SentimentUseCasesImpl(
            get(SCHEDULER_NEW_THREAD),
            get<SentimentRepository>()
        ) as SentimentUseCase
    }
}