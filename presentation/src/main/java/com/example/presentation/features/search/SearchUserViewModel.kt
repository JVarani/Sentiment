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

    private val errorLv = MutableLiveData<Unit>()
    fun mustShowError(): LiveData<Unit> = errorLv

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
}