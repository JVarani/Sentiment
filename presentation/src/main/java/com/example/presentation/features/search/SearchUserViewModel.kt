package com.example.presentation.features.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.domain.twitter.TwitterUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.features.search.mappers.PTweetMapper
import com.example.presentation.features.search.models.PTweet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class SearchUserViewModel(
    private val useCase: TwitterUseCase,
    private val pTweetMapper: PTweetMapper
) : BaseViewModel() {

    private val loadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = loadingLv

    private val tWeetsLv = MutableLiveData<List<PTweet>>()
    fun getTweets(): LiveData<List<PTweet>> = tWeetsLv

    fun onSearchTweets(name: String) {
        useCase
            .searchTweets(name)
            .map(pTweetMapper::transform)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loadingLv.postValue(true)
            }
            .doAfterTerminate {
                loadingLv.postValue(false)
            }
            .subscribeBy(
                onSuccess = { list ->
                    tWeetsLv.postValue(list)
                },
                onError = {
                    Log.i("TEST", it.message)
                }
            )
    }
}