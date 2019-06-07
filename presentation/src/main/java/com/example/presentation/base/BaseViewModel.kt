package com.example.presentation.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.koin.standalone.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    open val loadingGeneralLv = MutableLiveData<Boolean>()
    fun isLoadingGeneral(): LiveData<Boolean> = loadingGeneralLv

    open val errorDialogResIdLv = MutableLiveData<Int>()
    fun mustShowErrorDialog(): LiveData<Int> = errorDialogResIdLv
}