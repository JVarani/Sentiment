package com.example.data.twitter

import android.util.Base64
import com.example.data.BuildConfig
import com.example.data.twitter.mappers.DTweetMapper
import com.example.data.twitter.mappers.DTwitterAuthMapper
import com.example.domain.twitter.TwitterRepository
import com.example.domain.twitter.models.Tweet
import com.example.domain.twitter.models.TwitterAuth
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TwitterRepositoryImpl(
    private val twitterRemoteSource: TwitterRemoteSource,
    private val dTwitterAuthMapper: DTwitterAuthMapper,
    private val dTweetMapper: DTweetMapper
) : TwitterRepository {

    private fun getAuthToken(): TwitterAuth {
        val auth = "Basic ${Base64.encodeToString(
            "${BuildConfig.CONSUMER_KEY}:${BuildConfig.CONSUMER_SECRET}".toByteArray(),
            Base64.NO_WRAP
        )}"

        return twitterRemoteSource
            .getAuthToken(auth)
            .subscribeOn(Schedulers.newThread())
            .map(dTwitterAuthMapper::transform)
            .blockingGet()
    }

    override fun searchTweets(name: String): Single<List<Tweet>> {
        return twitterRemoteSource
            .searchTweets("Bearer ${getAuthToken().accessToken}", name)
            .subscribeOn(Schedulers.newThread())
            .map(dTweetMapper::transform)
    }
}