package com.example.domain.twitter

import com.example.domain.twitter.models.Tweet
import io.reactivex.Single

interface TwitterRepository {

    fun searchTweets(
        name: String
    ): Single<List<Tweet>>
}