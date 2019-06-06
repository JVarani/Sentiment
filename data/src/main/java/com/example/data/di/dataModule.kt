package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.twitter.TwitterRemoteSource
import com.example.data.twitter.TwitterRepositoryImpl
import com.example.data.twitter.mappers.DTweetMapper
import com.example.data.twitter.mappers.DTwitterAuthMapper
import com.example.domain.twitter.TwitterRepository
import com.google.gson.Gson
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_TWITTER_API_URL = "twitterApiUrl"
const val KOIN_RETROFIT_TWITTER = "retrofitTwitter"


val dataModule = applicationContext {

    bean {
        get<BuildConfig>()
    }

    bean {
        Gson()
    }

    bean(KOIN_TWITTER_API_URL) {
        BuildConfig.TWITTER_API
    }

    bean {
        GsonConverterFactory.create(get<Gson>())
    }

    bean {
        RxJava2CallAdapterFactory.create()
    }

    // ========= RETROFIT TWITTER

    bean(KOIN_RETROFIT_TWITTER) {
        Retrofit
            .Builder()
            .baseUrl(get<String>(KOIN_TWITTER_API_URL))
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    bean {
        DTweetMapper()
    }

    bean {
        DTwitterAuthMapper()
    }

    // ========= TWITTER

    bean {
        get<Retrofit>(KOIN_RETROFIT_TWITTER).create(TwitterRemoteSource::class.java)
    }

    bean {
        TwitterRepositoryImpl(
            get<TwitterRemoteSource>(),
            get<DTwitterAuthMapper>(),
            get<DTweetMapper>()
        ) as TwitterRepository
    }
}