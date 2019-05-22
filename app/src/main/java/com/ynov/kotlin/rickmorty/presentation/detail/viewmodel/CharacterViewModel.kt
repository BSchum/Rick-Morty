package com.ynov.kotlin.rickmorty.presentation.detail.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterViewModel: ViewModel() {
    var characterLiveData : MutableLiveData<RMCharacter> = MutableLiveData()

    fun retrieveCharacter(id: Int){
        RMApplication.app.repo
            .retrieveCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                }
            )
    }

}

