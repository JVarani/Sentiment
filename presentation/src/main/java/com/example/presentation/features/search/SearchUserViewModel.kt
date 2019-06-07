package com.example.presentation.features.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.domain.sentiment.SentimentUseCase
import com.example.domain.twitter.TwitterUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.features.search.enums.PSentiment
import com.example.presentation.features.search.mappers.PSentimentMapper
import com.example.presentation.features.search.mappers.PTweetMapper
import com.example.presentation.features.search.models.PTweet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class SearchUserViewModel(
    private val useCaseTwitter: TwitterUseCase,
    private val pTweetMapper: PTweetMapper,
    private val useCaseSentiment: SentimentUseCase,
    private val pSentimentMapper: PSentimentMapper
) : BaseViewModel() {

    private val errorLv = MutableLiveData<Unit>()
    fun mustShowError(): LiveData<Unit> = errorLv

    private val loadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = loadingLv

    private val tWeetsLv = MutableLiveData<List<PTweet>>()
    fun getTweets(): LiveData<List<PTweet>> = tWeetsLv

    private val sentimentLv = MutableLiveData<PSentiment>()
    fun getSentiment(): LiveData<PSentiment> = sentimentLv

    fun onSearchTweets(name: String) {
        useCaseTwitter
            .searchTweets(name)
            .map(pTweetMapper::transform)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loadingLv.postValue(true)
            }
            .subscribeBy(
                onSuccess = { list ->
                    loadingLv.postValue(false)
                    if (list.isEmpty())
                        errorLv.postValue(Unit)
                    else {
                        tWeetsLv.postValue(list)
                    }
                },
                onError = {
                    loadingLv.postValue(false)
                    errorLv.postValue(Unit)
                    Log.i("TEST", it.message)
                }
            )
    }

    fun onAnalyzeTweet(tweet: String) {
        useCaseSentiment
            .analyzeTweet(tweet)
            .map(pSentimentMapper::transform)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loadingGeneralLv.postValue(true)
            }
            .subscribeBy(
                onSuccess = { sentiment ->
                    loadingGeneralLv.postValue(false)
                    sentimentLv.postValue(sentiment)
                },
                onError = {
                    loadingGeneralLv.postValue(false)
                    errorDialogResIdLv.postValue(R.string.error_message)
                }
            )
    }
}