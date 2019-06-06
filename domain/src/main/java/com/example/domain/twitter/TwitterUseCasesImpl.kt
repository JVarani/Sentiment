package com.example.domain.twitter

import com.example.domain.twitter.models.Tweet
import io.reactivex.Scheduler
import io.reactivex.Single

class TwitterUseCasesImpl(
    private val scheduler: Scheduler,
    private val repository: TwitterRepository
) : TwitterUseCase {

    override fun searchTweets(name: String): Single<List<Tweet>> {
        return repository.searchTweets(name)
            .subscribeOn(scheduler)
    }

}