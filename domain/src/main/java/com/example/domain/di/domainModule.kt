package com.example.domain.di

import com.example.domain.twitter.TwitterRepository
import com.example.domain.twitter.TwitterUseCase
import com.example.domain.twitter.TwitterUseCasesImpl
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.applicationContext

const val SCHEDULER_IO = "schedulerIo"

val domainModule = applicationContext {

    bean(SCHEDULER_IO) {
        Schedulers.io()
    }

    // ========== CLUBE ALELO

    factory {
        TwitterUseCasesImpl(
            get(SCHEDULER_IO),
            get<TwitterRepository>()
        ) as TwitterUseCase
    }
}