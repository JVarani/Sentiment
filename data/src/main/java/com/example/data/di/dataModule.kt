package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.sentiment.SentimentRemoteSource
import com.example.data.sentiment.SentimentRepositoryImpl
import com.example.data.sentiment.mappers.DSentimentAnalyzerResponseObjectMapper
import com.example.data.sentiment.mappers.SentimentErrorMapper
import com.example.data.twitter.TwitterRemoteSource
import com.example.data.twitter.TwitterRepositoryImpl
import com.example.data.twitter.mappers.DTweetMapper
import com.example.data.twitter.mappers.DTwitterAuthMapper
import com.example.domain.sentiment.SentimentRepository
import com.example.domain.twitter.TwitterRepository
import com.google.gson.GsonBuilder
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_TWITTER_API_URL = "twitterApiUrl"
const val KOIN_RETROFIT_TWITTER = "retrofitTwitter"

const val KOIN_GOOGLE_LANGUAGE_API_URL = "googleLanguageApiUrl"
const val KOIN_RETROFIT_GOOGLE_LANGUAGE = "retrofitGoogleLanguage"


val dataModule = applicationContext {

    bean {
        get<BuildConfig>()
    }

    bean {
        GsonBuilder()
    }

    bean {
        GsonConverterFactory.create(get<GsonBuilder>().serializeNulls().create())
    }

    bean {
        RxJava2CallAdapterFactory.create()
    }

    // ========= RETROFIT TWITTER

    bean(KOIN_TWITTER_API_URL) {
        BuildConfig.TWITTER_API
    }

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


    // ========= RETROFIT GOOGLE LANGUAGE

    bean {
        DSentimentAnalyzerResponseObjectMapper()
    }

    bean(KOIN_GOOGLE_LANGUAGE_API_URL) {
        BuildConfig.GOOGLE_LANGUAGE_API
    }

    bean(KOIN_RETROFIT_GOOGLE_LANGUAGE) {
        Retrofit
            .Builder()
            .baseUrl(get<String>(KOIN_GOOGLE_LANGUAGE_API_URL))
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    // ========= GOOGLE LANGUAGE

    bean {
        get<Retrofit>(KOIN_RETROFIT_GOOGLE_LANGUAGE).create(SentimentRemoteSource::class.java)
    }

    bean {
        SentimentErrorMapper()
    }

    bean {
        SentimentRepositoryImpl(
            get<SentimentRemoteSource>(),
            get<DSentimentAnalyzerResponseObjectMapper>(),
            get<SentimentErrorMapper>()
        ) as SentimentRepository
    }
}